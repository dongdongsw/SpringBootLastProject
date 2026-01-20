package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

public interface ReserveService {
	
	public List<SeoulVO> seoulReserveData(Map map);
	
	public int seoulReserveTotalPage(String address);
	
	public String reserveInsert(ReserveVO vo);
	
	public List<ReserveVO> reserveMyData(String id);
	
	public List<ReserveVO> reserveAdminData();

}
