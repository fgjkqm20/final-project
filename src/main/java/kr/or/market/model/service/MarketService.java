package kr.or.market.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.market.model.dao.MarketDao;
import kr.or.market.model.vo.DogType;
import kr.or.market.model.vo.MarketDog;
import kr.or.market.model.vo.MarketDogFile;
import kr.or.member.model.vo.Member;

@Service
public class MarketService {
	@Autowired
	private MarketDao dao;

	public MarketDog selectOne(int marketNo) {
		MarketDog md = dao.selectOne(marketNo);
		ArrayList<MarketDogFile>fileList = dao.selectMarketNoFile(marketNo);
		md.setFileList(fileList);
		return md;
	}

	public int marketListCnt(MarketDog md) {
		return dao.marketListCnt(md);
	}

	public ArrayList<MarketDog> filterSelect(MarketDog md) {
		ArrayList<MarketDog>list = dao.fiterSelect(md);
		ArrayList<MarketDogFile>fileList = dao.selectFile();
		for(MarketDog m : list) {
			ArrayList<MarketDogFile> inputList = new ArrayList<MarketDogFile>();
			for(int i=0;i<fileList.size();i++) {
				if(fileList.get(i).getMarketNo() == m.getMarketNo()) {
					inputList.add(fileList.get(i));
					m.setFileList(inputList);
				}
			}
		}
		return list;
	}

	public int inputMarket(MarketDog md, Integer[] procedure) {
		// TODO Auto-generated method stub
		dao.inputMarket(md);
		int marketNo = dao.selectMarketNo();
		
		ArrayList<MarketDogFile> mdf = new ArrayList<MarketDogFile>();
		
		mdf = md.getFileList();
		System.out.println("procedure를 포함한 service에서 mdf"+mdf);
		
		for(int i=0;i<mdf.size();i++) {
			mdf.get(i).setMarketNo(marketNo);
			dao.inputMarketFile(mdf.get(i));
		}
		return 0;
	}

	public ArrayList<DogType> selectTypeList(Integer userInput) {
		return dao.selectTypeList(userInput);
	}

	public ArrayList<MarketDog> myMarketList(Member m) {
		ArrayList<MarketDog>list=dao.myMarketList(m);
		ArrayList<MarketDogFile>fileList = dao.selectFile();
		for(MarketDog md : list) {
			ArrayList<MarketDogFile> inputList = new ArrayList<MarketDogFile>();
			for(int i=0;i<fileList.size();i++) {
				if(fileList.get(i).getMarketNo() == md.getMarketNo()) {
					inputList.add(fileList.get(i));
					md.setFileList(inputList);
				}
			}
		}
		return list;
	}

	public int updateMarket(MarketDog md, Integer[] pastFileNo, MultipartFile[] photo) {
		dao.updateMarket(md);
		
		ArrayList<MarketDogFile> mdf = new ArrayList<MarketDogFile>();
		mdf = md.getFileList();
		
		if(mdf == null) {
			return 0;
		}else {
			for(Integer i:pastFileNo) {
				dao.deleteMarketFile(i);
			}
			for(int i=0;i<mdf.size();i++) {
				dao.updateMarketFile(mdf.get(i));
			}
		}
		return 0;
	}

	public int deleteMarket(int marketNo) {
		return dao.deleteMarket(marketNo);
	}
}
