package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.SeoulVO;

@Mapper
@Repository
public interface SeoulMapper {

	/*
	 <select id="seoulListData" resultType="com.sist.web.vo.SeoulVO" parameterType="hashmap">
 		SELECT no, contentid, title, address, image1, hit
 		FROM seoultravel
 		WHERE contenttype = #{contenttype} 
 		ORDER BY no ASC
 		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
 	</select>
	 */
	

	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoultravel "
			+ "WHERE contenttype = #{contenttype}")
	public int seoulLocationTotalPage(int contenttype);
	
	
	/*
	 <select id="seoulAttractionDetailData" resultMap="attMap" parameterType="int">
 		SELECT st.no, st.contentid, title, address, image1, x, y, hit, at.infocenter, at.resdate, at.usetime, at.parking, at.msg
 		FROM seoultravel st JOIN attraction at
 		ON st.contentid = at.contentid
 		WHERE st.contentid = #{contentid}
 	</select>
 	
 	<update id="seoulHitIncrement" parameterType="int">
 		UPDATE seoultravel SET
 		hit = hit + 1
 		WHERE contentid = #{contentid}
 	</update>
	 */
	public SeoulVO seoulAttractionDetailData(int contentid);
	
	public void seoulHitIncrement(int contentid);
	
	/*
	 <select id="seoulFindData" resultType="com.sist.web.vo.SeoulVO" parameterType="hashmap">
 		SELECT no, contentid, title, address, image1, hit, contenttype
 		FROM seoultravel
 		WHERE address LIKE '%'||#{address}||'%' 
 		ORDER BY no ASC
 		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
 	</select>
 	
 	<select id="seoulFindTotalPage" resultType="int">
 		SELECT CEIL(COUNT(*)/12.0)
 		FROM seoultravel
 		WHERE address LIKE '%'||#{address}||'%' 
 	</select>
	 */
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	
	/*
	 * <select id="seoulTop5Data" resultType="com.sist.web.vo.SeoulVO">
 		SELECT no, contentid, title, address, image1, hit, contenttype, rownum
 		FROM (SELECT SELECT no, contentid, title, address, image1, hit, contenttype
 		FROM seoultravel ORDER BY hit DESC)
 		WHERE rownum &lt;=4
 	</select>
	 */
	public List<SeoulVO> seoulTop5Data();
	
	/*
	 * - fetstival - 
	 	private int no, contentid;		
		private String eventstartdate, eventenddate, agelimit, playtime, eventplace, eventhomepage, usetime, spendtime;		
		private String msg;
		
		 image1, x, y, title, address
		 
		 - FoodStoreVO -
		 private int no, contentid;
	
	private String firstmenu, treatmenu, opendate, opentime;
	
	private String infocenter, restdate, parking;
	
	private String msg; 
	 */
	
	@Results({
		@Result(property = "fvo.eventstartdate", column = "eventstartdate"),
		@Result(property = "fvo.eventenddate", column = "eventenddate"),
		@Result(property = "fvo.agelimit", column = "agelimit"),
		@Result(property = "fvo.playtime", column = "playtime"),
		@Result(property = "fvo.eventplace", column = "eventplace"),
		@Result(property = "fvo.eventhomepage", column = "eventhomepage"),
		@Result(property = "fvo.usetime", column = "usetime"),
		@Result(property = "fvo.spendtime", column = "spendtime"),
		@Result(property = "fvo.msg", column = "msg")
		
	})
	@Select("SELECT f.no, image1, x, y, title, address "
			+ "eventstartdate, eventenddate, agelimit, playtime, eventplace, eventhomepage, usetime, spendtime, "
			+ "msg "
			+ "FROM seoultravel s, festival f "
			+ "WHERE s.contentid = f.contentid "
			+ "AND s.contentid = #{contentid}")
	public SeoulVO seoulFestivalDetailData(int contentid);
	
	@Results({
		@Result(property = "fsvo.firstmenu", column = "firstmenu"),
		@Result(property = "fsvo.treatmenu", column = "treatmenu"),
		@Result(property = "fsvo.infocenter", column = "infocenter"),
		@Result(property = "fsvo.parking", column = "parking"),
		@Result(property = "fsvo.opendate", column = "opendate"),
		@Result(property = "fsvo.opentime", column = "opentime"),
		@Result(property = "fsvo.restdate", column = "restdate"),
		@Result(property = "fsvo.msg", column = "msg")
		
	})
	@Select("SELECT f.no, image1, x, y, title, address, "
			+ "firstmenu, treatmenu, infocenter, opendate, opentime, infocenter, restdate, parking "
			+ "msg "
			+ "FROM seoultravel s, foodstore f "
			+ "WHERE s.contentid = f.contentid "
			+ "AND s.contentid = #{contentid}")
	public SeoulVO seoulFoodStoreDetailData(int contentid);
}
