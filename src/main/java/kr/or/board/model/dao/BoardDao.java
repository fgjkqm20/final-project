package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.QnaBoard;
import kr.or.board.model.vo.QnaComment;
import kr.or.board.model.vo.QnaFile;
import kr.or.notice.model.vo.Notice;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	//문의내역 list
	public ArrayList<QnaBoard> allQnaBoard() {
		List list = sqlsession.selectList("qnaboard.allQnaBoard");
		return (ArrayList<QnaBoard>)list;
		}
	//문의내역 insert
	public int insertQnaBoard(QnaBoard q) {
		return sqlsession.insert("qnaboard.insertQnaBoard",q);
	}
	//문의내역 게시판 번호 가져옴 
	public int selectQnaBoardNo() {
		return sqlsession.selectOne("qnaboard.selectQnaBoardNo");
	}
	//문의내역 file insert
	public int insertFile(QnaFile qf) {
		return sqlsession.insert("qnaboard.insertFile",qf);
	}
	//문의내역 view
	public QnaBoard selectOneQna(int qnaNo) {
		return sqlsession.selectOne("qnaboard.selectOneQna",qnaNo);
	}
	public ArrayList<QnaFile> selectFileList(int qnaNo) {
		List list = sqlsession.selectList("qnaboard.selectFileList");
		return (ArrayList<QnaFile>) list;
	}
	//문의내역  삭제 
	public int qnaBoardDelete(int qnaNo) {
		return sqlsession.delete("qnaboard.qnaBoardDelete",qnaNo);
	}
	//문의내역 댓글 insert
	public int insertQnaComment(QnaComment qc) {
		return sqlsession.insert("qnaboard.insertQnaComment",qc);
	}
	//문의내역 댓글 삭제  
	public int deleteQnaComment(QnaComment qc) {
		return sqlsession.delete("qnaboard.deleteQnaComment",qc);
	}
	//문의내역 댓글 조회 
	public ArrayList<QnaComment> commentListView(int qnaNo) {
		List list = sqlsession.selectList("qnaboard.commentListView",qnaNo);
		return (ArrayList<QnaComment>)list;
	}
	//문의내역 수정 
	public int updateQnaBoard(QnaBoard q) {
		return sqlsession.update("qnaboard.updateQnaBoard",q);
	}
	//문의내역 파일 수정
	public int updateFile(QnaFile qf) {
		return sqlsession.update("qnaboard.updateQnaFile",qf);
	}
	//문의내역 댓글 수정  
	public int updateQnaComment(QnaComment qc) {
		return sqlsession.update("qnaboard.updateQnaComment",qc);
	}
	//문의내역 파일 삭제 
	public int deleteFile(int fileNo) {
		return sqlsession.delete("qnaboard.deleteFile",fileNo);
	}
	//게시판 검색창  
	public ArrayList<QnaBoard> searchQnaBoard(QnaBoard q) {
		List list = sqlsession.selectList("qnaboard.searchQnaBoard",q);
		return (ArrayList<QnaBoard>)list;
	}
	//게시판 조회수 
	public int updateHit(int qnaNo) {
		return sqlsession.update("qnaboard.updateHit",qnaNo);
	}
	/*
	//문의내역 더보기 
	public ArrayList<QnaBoard> moreQna(int start, int amount) {
		List list = sqlsession.selectList("qnaboard.moreQna",start,amount);
		return (ArrayList<QnaBoard>)list;
	}
*/
}
