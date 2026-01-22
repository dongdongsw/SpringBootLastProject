package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.BusanService;
import com.sist.web.service.JejuService;
import com.sist.web.service.RealFindDataService;
import com.sist.web.service.SeoulService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVo;
import com.sist.web.vo.RealFindDataVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final BusanService bService;
	private final SeoulService sService;
	private final JejuService jService;

	private final RealFindDataService rService;
	
	@GetMapping("/main")
	public String main_main(Model model) {
		
		List<BusanVO> bList = bService.busanTop4Data();
		for(BusanVO vo : bList) {
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
		List<SeoulVO> sList = sService.seoulTop5Data();
		for(SeoulVO vo : sList) {
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
		List<JejuVo> jList = jService.jejuTop4Data();
		for(JejuVo vo : jList) {
			String[] datas = vo.getAddress().split(" ");
			vo.setAddress(datas[0] + " " + datas[1]);
		}
		
		List<RealFindDataVO> rList = rService.realFindDataAllData();
		
		
		model.addAttribute("rList",rList);
		model.addAttribute("jList",jList);
		model.addAttribute("bList",bList);
		model.addAttribute("sList",sList);
		
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
	
	
	
}
