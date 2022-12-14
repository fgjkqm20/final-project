package kr.or.walk.model.vo;

import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Walk {
	private int wmNo;						// 게시물 번호
	private int wmLeader;					// 모임 장
	private String wmTitle;					// 게시물 제목
	private String wmSubTitle;				// 게시물 부제목
	private String wmTag;					// 산책갈개 태그
	private String wmMeetTime; 				// 산책 시작 시간 
	private String wmAddr; 					// 산책 만남 장소
	private String wmRangeMember;			// 산책갈개 모임 최대 인원
	private String wmContent;				// 산책갈개 내용
	private int reply;						// 산책갈개 댓글수
	private String wmDate;					// 산책갈개 작성일
	private String rnum;					
	private String leaderNickname;
	private ArrayList<WmApply>	wList;		// 참여 멤버 프로필들
	private ArrayList<WalkFile> fileList;	//
	private ArrayList<WalkMateComment> wmcList;
	private String leaderPhoto;
}
