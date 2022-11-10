package kr.or.notice.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileRename;
import kr.or.notice.model.service.NoticeService;
import kr.or.notice.model.vo.Notice;
import kr.or.notice.model.vo.NoticeFile;
import kr.or.notice.model.vo.NoticePageData;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	//공지사항 이동
	@RequestMapping (value="/notice.do")
	public String notice(int reqPage ,Model model) {
		NoticePageData npd = service.noticeList(reqPage);
		model.addAttribute("list",npd.getList());
		model.addAttribute("pageNavi",npd.getPageNavi());
		model.addAttribute("reqPage",npd.getReqPage());
		model.addAttribute("numPerPage",npd.getNumPerPage());
		return "board/notice";
	}
	
	//공지사항 글쓰기 이동
	@RequestMapping(value="/writeNoticeFrm.do")
	public String writeNoticeFrm() {
		return "board/writeNotice";
	}
	
	//공지사항 insert
	@RequestMapping(value="/writeNotice.do")
	public String writeNotice(Notice n, MultipartFile[] noticeFile, HttpServletRequest request) {
		ArrayList<NoticeFile> fileList = new ArrayList<NoticeFile>();
		if(!noticeFile[0].isEmpty()) {
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
			for(MultipartFile file : noticeFile ) {
				String filename = file.getOriginalFilename();
				String filepath = FileRename.fileRename(savePath, filename);
				try {
					FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				NoticeFile nf = new NoticeFile();
				nf.setFilename(filename);
				nf.setFilepath(filepath);
				fileList.add(nf);
			}
		}
		n.setFileList(fileList);
		int result = service.insertNotice(n);
		return "redirect:/notice.do?reqPage=1";
	}
	
	//공지사항 상세보기 
	@RequestMapping(value="/noticeView.do")
	public String noticeView(int noticeNo, Model model) {
		Notice n = service.selectOneNotice(noticeNo);
		System.out.println(n);
		model.addAttribute("n",n);
		return "board/noticeView";
	}
	
	//공지사항 삭제 
	@RequestMapping(value="/noticeDelete.do")
	public String noticeDelete(int noticeNo, HttpServletRequest request) {
		System.out.println(noticeNo);
		ArrayList<NoticeFile>list = service.deleteNotice(noticeNo);
		//파일삭제
		if(list != null) {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
			for(NoticeFile file : list) {
				File delFile = new File(path+file.getFilepath());
				delFile.delete();
			}
		}
		return "redirect:/notice.do?reqPage=1";
	}
	
	//공지사항 수정페이지 이동 
	@RequestMapping(value="/noticeUpdateFrm.do")
	public String noticeUpdateFrm(int noticeNo,Model model ) {
		Notice n = service.selectOneNotice(noticeNo);
		model.addAttribute("n",n);
		return "board/noticeUpdate";
	}
	//공지사항 수정
	@RequestMapping(value="/noticeUpdate.do")
	public String noticeUpdate(Notice n, int[]fileNoList, String[]filepathList,MultipartFile[]noticeFile, HttpServletRequest request) {
		System.out.println(n);
		ArrayList<NoticeFile>fileList = new ArrayList<NoticeFile>();
		String savepath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
		
		//게시물 수정시 추가로 첨부한 파일 업로드
		if(!noticeFile[0].isEmpty()) {
			for(MultipartFile file: noticeFile) {
				String filename = file.getOriginalFilename();
				String filepath = FileRename.fileRename(savepath, filename);
				File upFile = new File(savepath+filepath);
				try {
					FileOutputStream fos = new FileOutputStream(upFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
					NoticeFile nf = new NoticeFile();
					nf.setFilename(filename);
					nf.setFilepath(filepath);
					fileList.add(nf);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//forEach end
		}
		
		n.setFileList(fileList);
		int result = service.updateNotice(n,fileNoList);
		if(fileNoList != null &&(result == (fileList.size()+fileNoList.length+1))) {
			if(filepathList != null) {
				for(String filepath : filepathList) {
					File delFile = new File(savepath+filepath);
					delFile.delete();
				}
			}
		}
		System.out.println(n);
		return "redirect:/noticeView.do?noticeNo="+n.getNoticeNo();
	}
}
