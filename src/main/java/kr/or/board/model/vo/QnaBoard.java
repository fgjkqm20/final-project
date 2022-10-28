package kr.or.board.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaBoard {
	private int qnaNo;
	private String qnaWriter;
	private String qnaTitle;
	private String qnaContent;
	private int qnaViews;
	private String qnaDate;
	private int qnaCateNo;
	private int qnaStatus;
	private int qnaSecret;
	ArrayList<QnaFile> fileList;
}
