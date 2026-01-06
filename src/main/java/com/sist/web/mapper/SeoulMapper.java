package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
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
}
