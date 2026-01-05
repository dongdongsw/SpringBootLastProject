package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BusanVO;

public interface BusanService {

	public List<BusanVO> busanListData(Map map);
	
	public int busanTotalPage(int contenttype);
}
