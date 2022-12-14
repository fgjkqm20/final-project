package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;
import kr.or.dog.model.vo.Dog;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MyCalendar;
import kr.or.member.model.vo.Report;
import kr.or.walk.model.vo.AppliedWalkInfo;
import kr.or.walk.model.vo.Walk;
import kr.or.walk.model.vo.WalkFile;
import kr.or.walk.model.vo.WmApply;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

//	public Member checkId(String kakao_email) {
//		return sqlSession.selectOne("member.checkId", kakao_email);
//	}

	public int insertKakao(Member m) {
		return sqlSession.insert("member.insertKakao", m);
	}

//	public int deleteKakao(String memberId) {
//		return sqlSession.delete("member.deleteMember", memberId);
//	}

	public Member selectOneMember(Member member) {
		return sqlSession.selectOne("member.selectOneMember", member);
	}

	public Member findId(Member member) {
		return sqlSession.selectOne("member.findId", member);
	}

	public int updatePw(Member m) {
		return sqlSession.update("member.updatePw", m);
	}

	public int insertMember(Member m) {
		return sqlSession.insert("member.insertMember", m);
	}

	public Member checkPhone(Member m) {
		return sqlSession.selectOne("member.checkPhone", m);
	}

	public int updateMember(Member m) {
		return sqlSession.update("member.updateMember", m);
	}

	public int deleteMember(String memberId) {
		return sqlSession.delete("member.deleteMember", memberId);
	}

	public ArrayList<MyCalendar> selectMyCalendar(String memberId) {
		List list = sqlSession.selectList("member.selectMyCalendar", memberId);
		return (ArrayList<MyCalendar>) list;
	}
	
	public ArrayList<DirectMessage> selectAllSendDm(HashMap<String, Object> map) {
		List list = sqlSession.selectList("member.selectAllSendDm", map);
		return (ArrayList<DirectMessage>) list;
	}

	public ArrayList<DirectMessage> selectAllReceiveDm(HashMap<String, Object> map) {
		List list = sqlSession.selectList("member.selectAllReceiveDm", map);
		return (ArrayList<DirectMessage>) list;
	}

	public DirectMessage selectOneSendDm(int dmNo) {
		return sqlSession.selectOne("member.selectOneSendDm", dmNo);
	}

	public DirectMessage selectOneReceiveDm(int dmNo) {
		return sqlSession.selectOne("member.selectOneReceiveDm", dmNo);
	}

	public int insertReplyDm(DirectMessage dm) {
		return sqlSession.insert("member.insertReplyDm", dm);
	}

	public int updateDmReadCheck(int dmNo) {
		return sqlSession.update("member.updateDmReadCheck", dmNo);
	}

	public int selectSendDmCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("member.selectSendDmCount", map);
	}
	
	public int selectReceiveDmCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("member.selectReceiveDmCount", map);
	}
	
	/*****************************************************/
	
	public Member selectPersonProfile(int memberNo) {
		return sqlSession.selectOne("member.selectPersonProfile", memberNo);
	}
	
	public ArrayList<Dog> selectDogList(int memberNo) {
		List list = sqlSession.selectList("member.selectDogList", memberNo);
		return (ArrayList<Dog>)list;
	}
	
	/*****************************************************/
	
	public int checkReportAble(Report report) {
		return sqlSession.selectOne("report.checkReportAble", report);
	}
	
	public int insertReport(Report report) {
		return sqlSession.insert("report.insertReport", report);
	}
	
	public ArrayList<Report> selectMyReportList(int reportMemberNo) {
		List list = sqlSession.selectList("report.selectMyReportList", reportMemberNo);
		return (ArrayList<Report>)list;
	}

	/*****************************************************/
	
	public ArrayList<AppliedWalkInfo> selectMyApplyList(String memberId) {
		List list = sqlSession.selectList("myWalkMate.selectMyApplyList", memberId);
		return (ArrayList<AppliedWalkInfo>)list;
	}

	public ArrayList<Walk> selectMyAttendList(int memberNo) {
		List list = sqlSession.selectList("myWalkMate.selectMyAttendList", memberNo);
		return (ArrayList<Walk>)list;
	}

	public ArrayList<Walk> selectOtherAttendList(String memberId) {
		List list = sqlSession.selectList("myWalkMate.selectOtherAttendList", memberId);
		return (ArrayList<Walk>)list;
	}

	public Walk selectOneWalkMate(int wmNo) {
		return sqlSession.selectOne("myWalkMate.selectOneWalkMate", wmNo);
	}
	
	public ArrayList<WalkFile> selectWalkMateFileList(int wmNo) {
		List list = sqlSession.selectList("myWalkMate.selectWalkMateFileList", wmNo);
		return (ArrayList<WalkFile>)list;
	}
	
	public Member selectLeaderProfile(int wmNo) {
		return sqlSession.selectOne("myWalkMate.selectLeaderProfile", wmNo);
	}
	
	public ArrayList<Member> selectAttendProfileList(int wmNo) {
		List list = sqlSession.selectList("myWalkMate.selectAttendProfileList", wmNo);
		return (ArrayList<Member>)list;
	}

	public int leaveWalkMate(int memberNo) {
		return sqlSession.delete("myWalkMate.leaveWalkMate", memberNo);
	}
	
	public ArrayList<WmApply> selectWalkMateApplyList(int wmNo) {
		List list = sqlSession.selectList("myWalkMate.selectWalkMateApplyList", wmNo);
		return (ArrayList<WmApply>)list;
	}
	
	public int updateApplyStat(WmApply wmApply) {
		return sqlSession.update("myWalkMate.updateApplyStat", wmApply);
	}

	public int updateWalkMate(Walk w) {
		return sqlSession.update("myWalkMate.updateWalkMate", w);
	}

	public int deleteWalkMate(int wmNo) {
		return sqlSession.delete("myWalkMate.deleteWalkMate", wmNo);
	}
}