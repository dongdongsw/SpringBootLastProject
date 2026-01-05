package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVo;

public interface JejuService {

	public List<JejuVo> jejuListData(Map map);
	
	public int jejuTotalPage(int contenttype);
}
