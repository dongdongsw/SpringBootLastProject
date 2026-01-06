package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

@Repository
@Mapper
public interface BusanMapper {


	public List<BusanVO> busanListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM busantravel "
			+ "WHERE contenttype = #{contenttype}")
	public int busanTotalPage(int contenttype);
	
	public List<BusanVO> busanFindData(Map map);
	public int busanFindTotalPage(String address);
	
	/*
	 <select id="busanTop4Data" resultType="com.sist.web.vo.BusanVO">
 		SELECT no, contentid, title, address, image1, hit, contenttype, rownum
 		FROM (SELECT SELECT no, contentid, title, address, image1, hit, contenttype
 		FROM busantravel ORDER BY hit DESC)
 		WHERE rownum &lt;=4
 	</select>
	 */
	public List<BusanVO> busanTop4Data();
}
