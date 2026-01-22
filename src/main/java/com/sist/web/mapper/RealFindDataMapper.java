package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.RealFindDataVO;

@Mapper
@Repository
public interface RealFindDataMapper {

	@Insert("INSERT INTO realFinData_1 VALUES( "
			+ "(SELECT NVL(MAX(no)+1,1) FROM realFindData), "
			+ "#{word}, #{images})")
	public void realFindDataInsert(RealFindDataVO vo);
	
	@Delete("DELETE FROM realFindData_1")
	public void realFindDataDelete();

	@Select("SELECT * FROM realFindData_1 ORDER BY no ASC")
	public List<RealFindDataVO> realFindDataAllData();
}
