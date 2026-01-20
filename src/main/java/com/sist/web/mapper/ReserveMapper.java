package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

@Mapper
@Repository
public interface ReserveMapper {

	
	public List<SeoulVO> seoulReserveData(Map map);
	
	
	public int seoulReserveTotalPage(String address);
	
	
	@Insert("INSERT INTO reserve_1(no, cno, id, rday, rtime, rinwon) "
			+ "VALUES(r1_no_seq.nextval, #{cno},#{id},#{rday},#{rtime},#{rinwon})")
	public void reserveInsert(ReserveVO vo);
	
//	@Results({
//		@Result(column = "title", property = "svo.title"),
//		@Result(column = "image1", property = "svo.image1"),            
//		@Result(column = "address", property = "svo.address")
//	})
	// vo.getSvo().setTitle()
	@ResultMap("resMap")
	@Select("SELECT r.no, cno, rday, rtime, rinwon, TO_CHAR(regdate,'yyyy-mm-dd') as dbday, "
			+ "isReserve, title, image1, address "
			+ "FROM reserve_1 r, seoulTravel s "
			+ "WHERE r.cno = s.contentid "
			+ "AND id = #{id} "
			+ "ORDER BY no DESC")
	public List<ReserveVO> reserveMyData(String id);
	
	@ResultMap("resMap")
	@Select("SELECT r.no, id, cno, rday, rtime, rinwon, TO_CHAR(regdate,'yyyy-mm-dd') as dbday, "
			+ "isReserve, title, image1, address "
			+ "FROM reserve_1 r, seoulTravel s "
			+ "WHERE r.cno = s.contentid "
			+ "ORDER BY no DESC")
	public List<ReserveVO> reserveAdminData();

}
