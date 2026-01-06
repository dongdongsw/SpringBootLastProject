package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVo;

public interface JejuService {
	// 일반 리스트
	public List<JejuVo> jejuListData(Map map);
	public int jejuTotalPage(int contenttype);
	
	// 검색
	public List<JejuVo> jejuFindData(Map map);
	public int jejuFindTotalPage(Map map);
	
	// top4 (메인페이지)
	public List<JejuVo> jejuTop4Data();
}
