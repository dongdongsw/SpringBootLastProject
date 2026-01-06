package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusanController {

	private final BusanService bService;
	
	@GetMapping("/busan/list")
	public String busan_location(@RequestParam(name="page", required = false) String page, @RequestParam("cno") int cno,
			Model model) {
		
		Map map = new HashMap<>();
		
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		map.put("start", (curpage-1) * 12);
		map.put("contenttype", cno);
		
		List<BusanVO> list = bService.busanListData(map);
		int totalpage = bService.busanTotalPage(cno);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		for(BusanVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
		
		String name = "";
		
		switch (cno) {
		    case 12:
		        name = "부산 관광지";
		        break;
		    case 14:
		        name = "부산 문화시설";
		        break;
		    case 15:
		        name = "부산 축제 & 공연";
		        break;
		    case 32:
		        name = "부산 숙박";
		        break;
		    case 38:
		        name = "부산 쇼핑";
		        break;
		    case 39:
		        name = "부산 음식";
		        break;
		    default:
		        name = "";
		}
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
		
		model.addAttribute("main_jsp", "../busan/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/busan/find")
	public String busan_find(Model model) {
		
		model.addAttribute("main_jsp","../busan/busan_find.jsp");
		return "main/main";
	}
}
