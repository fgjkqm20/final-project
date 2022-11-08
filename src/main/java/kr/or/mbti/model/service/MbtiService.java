package kr.or.mbti.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.mbti.model.dao.MbtiDao;
import kr.or.mbti.model.vo.MbtiData;
import kr.or.mbti.model.vo.MbtiResult;

@Service
public class MbtiService {

	@Autowired
	private MbtiDao dao;

	@Transactional
	public int insertMbtiType(MbtiData md) {
		return dao.insertMbtiType(md);
	}

	public MbtiResult selectMbtiResult(MbtiResult mr) {
		return dao.selectMbtiResult(mr.getAnswers());
	}
}
