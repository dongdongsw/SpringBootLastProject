package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.mapper.JejuMapper;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JejuServiceImpl implements JejuService{

	private final JejuMapper mapper;

	@Override
	public List<JejuVo> jejuListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.jejuListData(map);
	}

	@Override
	public int jejuTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return mapper.jejuTotalPage(contenttype);
	}

	@Override
	public List<JejuVo> jejuFindData(Map map) {
		// TODO Auto-generated method stub
		return mapper.jejuFindData(map);
	}

	@Override
	public int jejuFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return mapper.jejuFindTotalPage(map);
	}

	@Override
	public List<JejuVo> jejuTop4Data() {
		// TODO Auto-generated method stub
		return mapper.jejuTop4Data();
	}

	
}
