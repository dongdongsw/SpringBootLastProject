package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

public interface BusanService {

	// 리스트
	public List<BusanVO> busanListData(Map map);
	public int busanTotalPage(int contenttype);
	
	// 검색

	public List<BusanVO> busanFindData(Map map);
	public int busanFindTotalPage(String address);
	
	// top4 (메인페이지)
	public List<BusanVO> busanTop4Data();
}
