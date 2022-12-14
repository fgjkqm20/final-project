package kr.or.member.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import common.FileRename;
import kr.or.dm.model.vo.DirectMessage;
import kr.or.dog.model.service.DogService;
import kr.or.dog.model.vo.Dog;
import kr.or.member.model.service.MemberService;
import kr.or.member.model.service.MessageService;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MyCalendar;
import kr.or.member.model.vo.Report;
import kr.or.walk.model.vo.AppliedWalkInfo;
import kr.or.walk.model.vo.Walk;
import kr.or.walk.model.vo.WmApply;

@Controller
public class memberController {
	@Autowired
	private MemberService service;
	@Autowired
	private DogService dogService;
	@Autowired
	private MessageService msgService;
	@Autowired
	private FileRename fileRename;
	
	@RequestMapping(value="/loginFrm.do")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	@RequestMapping(value="/joinSuccess.do")
	public String kakaoJoinSuccess() {
		return "member/joinSuccess";
	}
	
	// ???????????? ?????????
	@RequestMapping(value="/kakaoLogin.do")
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpServletRequest req, Model model) throws Exception {
		System.out.println("--------- ????????? ???????????? ????????? ---------");

        // ???????????? ????????????(reqUrl)??? ?????? ?????? ????????????
        System.out.println("#########" + code);   
        String access_Token = getAccessToken(code); // ??????????????? ?????? ????????????
        System.out.println("###access_Token#### : " + access_Token); // ????????? ?????? ??????
        
        // ????????? ????????? ?????? ?????? ????????????
        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("------- access_Token ------- : " + access_Token);
        System.out.println("------- userInfo ------- : " + userInfo.get("email"));
        System.out.println("------- nickname ------- : " + userInfo.get("nickname"));
        
        String kakao_email = (String)userInfo.get("email");
        String kakao_nickname = (String)userInfo.get("nickname");
        
        Member member = new Member();
        member.setMemberId(kakao_email);
        Member m = service.selectOneMemberEnc(member);
        
        if(m == null){ // ?????? ??????????????? ????????? ??? ?????? ??????
        	System.out.println("?????? ????????? ??????");
        	HttpSession session = req.getSession(); // session ??????
        	session.setAttribute("access_Token", access_Token); // session ????????????
            return "member/kakaoJoin"; // ?????? DB??? ?????? ????????? ID??? ????????? ???????????? ???????????? ?????????
        } else { // ?????? ?????? ???????????? ??? ???????????????
        	if(m.getJoinType().equals("?????????")) {
        		System.out.println("???????????? ????????? ??????");
        		ArrayList<Dog> dogList = dogService.selectMyDogList(m.getMemberNo());
        		m.setDogList(dogList);
        		
        		HttpSession session = req.getSession(); // session ??????
        		session.setAttribute("m", m); // session ????????????
        		session.setAttribute("access_Token", access_Token); // session ????????????
        		
        		if(m.getMemberLevel() == 4) { // ?????? ????????? 4??? ???????????? ??????
        			model.addAttribute("title", "?????? ??????");
        			model.addAttribute("msg", "???????????? ?????? ????????? ????????? ??????????????????.");
        			model.addAttribute("icon", "error");
        			model.addAttribute("loc", "/logout.do");
    				return "common/msg";
    			}
        		
        		return "redirect:/";        		
        	} else {
        		System.out.println("???????????? ????????? ??????");
        		model.addAttribute("nickname", m.getMemberNickname());
        		model.addAttribute("jointype", m.getJoinType());
        		return "member/alreadyJoin";
        	}
        }
	}
	
	// ???????????? ?????? ???????????? ??? ???????????? ????????? ????????????
    @RequestMapping(value = "/selectMyAccessTocken.do")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code, String memberPhone, HttpServletRequest req, HttpSession session) throws Exception {

        System.out.println("--------- ????????? ???????????? ????????? ---------");
        System.out.println("--------- ????????? ?????? ??????");

        // ???????????? ????????????(reqUrl)??? ?????? ?????? ????????????
        System.out.println("#########" + code);   
        String access_Token = (String)session.getAttribute("access_Token");
        System.out.println("###access_Token#### : " + access_Token); // ????????? ?????? ??????

        // ????????? ????????? ?????? ?????? ????????????
        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("------- access_Token ------- : " + access_Token);
        System.out.println("------- userInfo ------- : " + userInfo.get("email"));
        System.out.println("------- nickname ------- : " + userInfo.get("nickname"));

        String kakao_email = (String)userInfo.get("email");
        String kakao_nickname = (String)userInfo.get("nickname");

        Member m = new Member();
        m.setMemberId(kakao_email);
        m.setMemberNickname(kakao_nickname);
        m.setMemberPhone(memberPhone);
        int result = service.insertKakao(m);
        
        System.out.println(result);
        return "redirect:/joinSuccess.do";
    }
	
	// ????????? ????????? ??? ????????? ?????? ??????
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // URL????????? ???????????? ?????? ??? ??? ??????, POST ?????? PUT ????????? ????????? setDoOutput??? true??? ???????????????
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=e400fe38f12604a2937ea759fe0166f7"); //????????? ???????????? REST API key
            sb.append("&redirect_uri=http://192.168.10.33/kakaoLogin.do"); // ????????? ????????? ?????? ?????? localhost
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // ?????? ????????? 200????????? ??????
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // ????????? ?????? ?????? JSON????????? Response ????????? ????????????
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Gson ?????????????????? ????????? ???????????? JSON?????? ?????? ??????
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    // ????????? ????????? ??? ????????? ?????? ??????
    public HashMap<String, Object> getUserInfo (String access_Token) {

        // ???????????? ????????????????????? ?????? ????????? ?????? ??? ????????? HashMap???????????? ??????
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // ????????? ????????? Header??? ????????? ??????
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
    
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		Member m = (Member)session.getAttribute("m");
		if(m.getJoinType().equals("?????????")) {
			service.kakaoLogout((String)session.getAttribute("access_Token"));			
		}
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/kakaoUnlink.do")
	public String unlink(HttpSession session, Model model) {
		Member m = (Member)session.getAttribute("m");
		String memberId = m.getMemberId();
		int result = service.kakaoUnlink((String)session.getAttribute("access_Token"), memberId);
		if(result > 0) {
			session.invalidate();
			model.addAttribute("title", "?????? ??????");
			model.addAttribute("msg", "?????? ????????? ?????????????????????.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");
			return "common/msg";
		} else {
			session.invalidate();
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session) {
		Member m = service.selectOneMemberEnc(member);
		if(m!=null) {
			ArrayList<Dog> dogList = dogService.selectMyDogList(m.getMemberNo());
			m.setDogList(dogList);
			session.setAttribute("m", m);
			
			if(m.getMemberLevel() == 4) { // ?????? ????????? 4??? ???????????? ??????
				return "loginLimit";
			}
			return "success";				
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value="/findIdFrm.do")
	public String findIdFrm() {
		return "member/findIdFrm";
	}
	
	@ResponseBody
	@RequestMapping(value="/findId.do", produces="application/json;charset=utf-8")
	public String findId(Member member) {
		Member m = service.findId(member);
		String text = "";
		if(m != null) {
			text = m.getMemberId() + "/" + m.getJoinType();
		} else {
			text = "???????????? ?????? ????????? ????????????.";
		}
		return new Gson().toJson(text);
	}
	
	@RequestMapping(value="/findPwFrm.do")
	public String findPwFrm() {
		return "member/findPwFrm";
	}
	
	@ResponseBody
	@RequestMapping(value="/findPw.do", produces="application/json;charset=utf-8")
	public String findPw(Member member, HttpSession session) {
		Member m = service.selectOneMemberEnc(member);
		String text = "";
		if(m != null) {
			if(m.getJoinType().equals("?????????")) {
				text = "kakao";
			} else {				
				session.setAttribute("updatePw", m);
				text = "find";
			}
		} else {
			text = "???????????? ?????? ????????? ????????????.";
		}
		return new Gson().toJson(text);
	}
	
	@ResponseBody
	@RequestMapping(value="/sendMsg.do")
	public String sendMsg(String memberPhone) {
		// 6?????? ???????????? ??????
		Random r = new Random();
		int rdNum = 0;
		String rdCode = "";
		String resultCode = "";
		
		for(int i=0; i<6; i++) {
			rdNum = r.nextInt(9);
			rdCode = Integer.toString(rdNum);
			resultCode += rdCode;
		}
		
		msgService.sendMessage(memberPhone, resultCode);
		return resultCode;
	}
	
	@RequestMapping(value="/updatePwFrm.do")
	public String updatePwFrm() {
		return "member/updatePwFrm";
	}
	
	@RequestMapping(value="/updatePw.do")
	public String updatePw(String updatePw, HttpSession session, Model model) {
		Member m = (Member)session.getAttribute("m");
		if(m == null) { // ????????? ??? ??? ???????????? ???????????? ????????? ???
			m = (Member)session.getAttribute("updatePw");
			m.setMemberPw(updatePw);
			int result = service.updatePwEnc(m);
			if(result > 0) {
				return "member/updatePwSuccess";			
			} else {
				return "redirect:/";
			}
		} else { // ???????????? ????????? ???????????? ????????? ???
			m.setMemberPw(updatePw);
			int result = service.updatePwEnc(m);
			if(result > 0) {
				model.addAttribute("title", "?????? ??????");
				model.addAttribute("msg", "??????????????? ?????????????????????.");
				model.addAttribute("icon", "success");
				model.addAttribute("loc", "/myPage.do");
				return "common/msg";			
			} else {
				return "redirect:/updatePwFrm.do";
			}
		}
	}
	
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@RequestMapping(value="/join.do")
	public String insertMember(Member m) {
		int result = service.insertMemberEnc(m);
		if(result > 0) {
			return "member/joinSuccess";
		} else {
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkId.do")
	public String checkId(Member m) {
		Member member = service.selectOneMemberEnc(m);
		if(member != null) {
			return "already";
		} else {
			return "possible";			
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkPhone.do")
	public String checkPhone(Member m) {
		Member member = service.checkPhone(m);
		if(member != null) { // ?????? ????????? ???????????????
			return "already";
		} else {
			return "possible";
		}
	}
	
	@RequestMapping(value="/myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member m, MultipartFile[] photo, HttpSession session, HttpServletRequest request, Model model) {
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/member/");
		
		if(photo != null) {
			for(MultipartFile file : photo) {
				String filename = file.getOriginalFilename();
				String filepath = fileRename.fileRename(savePath, filename);
				File upFile = new File(savePath + filepath);
				try {
					FileOutputStream fos = new FileOutputStream(upFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
					m.setMemberPhoto(filepath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // forEach??? ???		
		}
		
		int result = service.updateMember(m);
		if(result > 0) {
			Member member = service.selectOneMemberEnc(m);
			ArrayList<Dog> dogList = dogService.selectMyDogList(member.getMemberNo());
    		member.setDogList(dogList);
    		
			session.setAttribute("m", member);
			model.addAttribute("title", "?????? ??????");
			model.addAttribute("msg", "????????? ?????????????????????.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/myPage.do");
			return "common/msg";
		} else {			
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(String memberId, Model model) {
		int result = service.deleteMember(memberId);
		if(result > 0) {
			model.addAttribute("title", "?????? ??????");
			model.addAttribute("msg", "?????? ????????? ?????????????????????.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/logout.do");
			return "common/msg";
		} else {
			return "redirect:/myPage.do";
		}
	}
	
	@RequestMapping(value="/currentPw.do")
	public String currentPw() {
		return "member/currentPwFrm";
	}
	
	@RequestMapping(value="/checkPw.do")
	public String checkPw(Member m) {
		Member member = service.selectOneMemberEnc(m);
		if(member != null) {
			return "redirect:/updatePwFrm.do";
		} else {
			return "redirect:/currentPw.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/selectMyCalendar.do", produces="application/json;charset=utf-8")
	public String selectMyCalendar(@SessionAttribute Member m) {
		ArrayList<MyCalendar> list = service.selectMyCalendar(m.getMemberId());
		if(list != null) {
			return new Gson().toJson(list);
		} else {
			return "null";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/selectAllSendDm.do", produces="application/json;charset=utf-8")
	public String selectAllSendDm(@SessionAttribute Member m, int reqPage, DirectMessage dm) {
		int memberNo = m.getMemberNo();
		HashMap<String, Object> map = service.selectAllSendDm(memberNo, reqPage, dm);
		if(map != null) {
			return new Gson().toJson(map);
		} else {
			return "null";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/selectAllReceiveDm.do", produces="application/json;charset=utf-8")
	public String selectAllReceiveDm(@SessionAttribute Member m, int reqPage, DirectMessage dm) {
		int memberNo = m.getMemberNo();
		HashMap<String, Object> map = service.selectAllReceiveDm(memberNo, reqPage, dm);
		if(map != null) {
			return new Gson().toJson(map);
		} else {
			return "null";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/selectOneSendDm.do", produces="application/json;charset=utf-8")
	public String selectOneSendDm(int dmNo) {
		DirectMessage dm = service.selectOneSendDm(dmNo);
		return new Gson().toJson(dm);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectOneReceiveDm.do", produces="application/json;charset=utf-8")
	public String selectOneReceiveDm(int dmNo) {
		DirectMessage dm = service.selectOneReceiveDm(dmNo);
		return new Gson().toJson(dm);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertReplyDm.do")
	public String insertReplyDm(@SessionAttribute Member m, DirectMessage dm) {
		dm.setSenderNo(m.getMemberNo());
		dm.setSenderId(m.getMemberId());
		dm.setSenderName(m.getMemberNickname());
		
		int result = service.insertReplyDm(dm);
		return "result";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchSendDm.do", produces="application/json;charset=utf-8")
	public String searchSendDm(@SessionAttribute Member m, int reqPage, DirectMessage dm) {
		int memberNo = m.getMemberNo();
		HashMap<String, Object> map = service.searchSendDm(memberNo, reqPage, dm);
		if(map != null) {
			return new Gson().toJson(map);
		} else {
			return "null";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/searchReceiveDm.do", produces="application/json;charset=utf-8")
	public String searchReceiveDm(@SessionAttribute Member m, int reqPage, DirectMessage dm) {
		int memberNo = m.getMemberNo();
		HashMap<String, Object> map = service.searchReceiveDm(memberNo, reqPage, dm);
		if(map != null) {
			return new Gson().toJson(map);
		} else {
			return "null";
		}
	}
	/*****************************************************/
	
	// ????????? ??????
	@RequestMapping(value="/selectOneProfile.do")
	public String selectOneProfile(int memberNo, Model model) {
		Member other = service.selectOneProfile(memberNo);
		
		model.addAttribute("other", other);
		return "member/profile";
	}
	
	/*****************************************************/
	
	// ????????????
	@ResponseBody
	@RequestMapping(value="/insertReport.do", produces="application/text;charset=utf-8")
	public String insertReport(Report report, Model model) {
		int result = service.insertReport(report);
		
		if(result > 0) {
			return "????????? ?????????????????????. ????????? ?????? ???????????????.";
		}else if(result == -1) {
			return "?????? ????????? ???????????????.";
		}else {
			return "????????? ?????????????????????. ??????????????? ??????????????????.";
		}
	}
	
	// ??? ?????? ?????? ????????????
	@ResponseBody
	@RequestMapping(value="/selectMyReportList.do", produces="application/json;charset=utf-8")
	public String selectMyReportList(int reportMemberNo) {
		ArrayList<Report> list = service.selectMyReportList(reportMemberNo);
		
		return new Gson().toJson(list);
	}
	
	/*****************************************************/
	
	// ??? ??????????????? ???????????? ??????
	@RequestMapping(value="/myWalkMateList.do")
	public String myWalkMateList() {
		return "myWalkMate/myWalkMateList";
	}
	
	@ResponseBody
	@RequestMapping(value="/selectMyApplyList.do", produces="application/json;charset=utf-8")
	public String selectMyApplyList(String memberId) {
		ArrayList<AppliedWalkInfo> myApplyList = service.selectMyApplyList(memberId);
		
		return new Gson().toJson(myApplyList);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectMyAttendList.do", produces="application/json;charset=utf-8")
	public String selectMyAttendList(int memberNo) {
		ArrayList<Walk> list = service.selectMyAttendList(memberNo);
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectOtherAttendList.do", produces="application/json;charset=utf-8")
	public String selectOtherAttendList(String memberId) {
		ArrayList<Walk> list = service.selectOtherAttendList(memberId);
		
		return new Gson().toJson(list);
	}
	
	@RequestMapping(value="/walkMatePage.do")
	public String walkMatePage(int wmNo, Model model) {
		Walk w = service.selectOneWalkMate(wmNo);
		ArrayList<Member> attendList = service.selectAttendProfileList(wmNo);
		
		model.addAttribute("w", w);
		model.addAttribute("attendList", attendList);
		
		return "myWalkMate/walkMatePage/walkMatePage";
	}
	
	@ResponseBody
	@RequestMapping(value="/leaveWalkMate.do")
	public int leaveWalkMate(int memberNo) {
		return service.leaveWalkMate(memberNo);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectWalkMateApplyList.do", produces="application/json;charset=utf-8")
	public String selectWalkMateApplyList(int wmNo) {
		ArrayList<WmApply> list = service.selectWalkMateApplyList(wmNo);
				
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateApplyStat.do")
	public int updateApplyStat(WmApply wmApply) {
		return service.updateApplyStat(wmApply);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateWalkMate.do")
	public int updateWalkMate(Walk w) {
		return service.updateWalkMate(w);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteWalkMate.do")
	public int deleteWalkMate(int wmNo) {
		return service.deleteWalkMate(wmNo);
	}
}