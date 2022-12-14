package kr.or.dog.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dog.model.vo.Dog;
import kr.or.market.model.vo.DogType;

@Repository
public class DogDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Dog> selectMyDogList(int memberNo) {
		List list = sqlSession.selectList("dog.selectMyDogList", memberNo);
		return (ArrayList<Dog>) list;
	}

	public Dog selectMyOneDog(int dogNo) {
		return sqlSession.selectOne("dog.selectMyOneDog", dogNo);
	}

	public ArrayList<DogType> selectAllDogType() {
		List list = sqlSession.selectList("dog.selectAllDogType");
		return (ArrayList<DogType>) list;
	}

	public int insertMyDog(Dog d) {
		return sqlSession.insert("dog.insertMyDog", d);
	}

	public int deleteMyDog(int dogNo) {
		return sqlSession.delete("dog.deleteMyDog", dogNo);
	}

	public int updateMyDog(Dog d) {
		return sqlSession.update("dog.updateMyDog", d);
	}

	public int insertDogType(DogType dt) {
		return sqlSession.insert("dog.insertDogType", dt);
	}

	public int selectDogTypeCode() {
		return sqlSession.selectOne("dog.selectDogTypeCode");
	}
	
}
