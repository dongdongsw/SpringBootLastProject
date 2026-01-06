package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVo;
import com.sist.web.vo.SeoulVO;

@Repository
@Mapper
public interface JejuMapper {


	public List<JejuVo> jejuListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM jejutravel "
			+ "WHERE contenttype = #{contenttype}")
	public int jejuTotalPage(int contenttype);
	
	public List<JejuVo> jejuFindData(Map map);
	public int jejuFindTotalPage(Map map);
	
	/*
	 <select id="jejuTop4Data" resultType="com.sist.web.vo.JejuVo">
 		SELECT no, contentid, title, address, image1, hit, contenttype, rownum
 		FROM (SELECT SELECT no, contentid, title, address, image1, hit, contenttype
 		FROM jejutravel ORDER BY hit DESC)
 		WHERE rownum &lt;=4
 	</select>
	 */
	public List<JejuVo> jejuTop4Data();
}
