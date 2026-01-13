package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*
 * 	MVC : 오라클 / 컨트롤러 / JSP
 * 	 	  ------------- Vue/ React
 * 
 * 		=> SQL / 사용자가 어떤 데이터를 보낼지
 * 
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.SeoulService;
import com.sist.web.vo.SeoulVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class SeoulController {

	private final SeoulService sService;
	
	@GetMapping("/seoul/list")
	public String seoul_location(@RequestParam(name="page", required = false) String page, @RequestParam("cno") int cno,
			Model model) {
		
		Map map = new HashMap<>();
		
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		map.put("start", (curpage-1) * 12);
		map.put("contenttype", cno);
		
		List<SeoulVO> list = sService.seoulListData(map);
		int totalpage = sService.seoulLocationTotalPage(cno);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		for(SeoulVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
		
		String name = "";
		
		switch (cno) {
		    case 12:
		        name = "서울 관광지";
		        break;
		    case 14:
		        name = "서울 문화시설";
		        break;
		    case 15:
		        name = "서울 축제 & 공연";
		        break;
		    case 32:
		        name = "서울 숙박";
		        break;
		    case 38:
		        name = "서울 쇼핑";
		        break;
		    case 39:
		        name = "서울 음식";
		        break;
		    default:
		        name = "";
		}
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
		
		model.addAttribute("main_jsp", "../seoul/list.jsp");
		return "main/main";
	}
	
	
	@GetMapping("/seoul/detail_before")
	public String seoul_detail_before(
			@RequestParam("contentid") int contentid, 
			@RequestParam("contenttype") int contenttype, 
			HttpServletResponse response, 
			RedirectAttributes ra) {
		
		Cookie cookie = new Cookie("seoul_" + contentid, String.valueOf(contentid));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		ra.addAttribute("contentid", contentid);
		ra.addAttribute("contenttype", contenttype);
		return "redirect:/seoul/detail";
	}
	
	@GetMapping("/seoul/detail")
	public String seoul_detail(
			@RequestParam("contentid") int contentid, 
			@RequestParam("contenttype") int contenttype, 
			HttpServletResponse response,
			HttpServletRequest request,
			RedirectAttributes ra,
			Model model) {
			
		
		

		
		String jsp = "";
		if(contenttype == 12) {
			SeoulVO vo = sService.seoulAttractionDetailData(contentid);
			String[] addr = vo.getAddress().split(" ");
			model.addAttribute("addr" , addr[1].trim());
			model.addAttribute("vo",vo);
			jsp = "../seoul/attraction.jsp";
		}
		else if(contenttype == 14) {
			SeoulVO vo = sService.seoulAttractionDetailData(contentid);
			model.addAttribute("vo",vo);
			jsp = "../seoul/culture.jsp";
		}
		else if(contenttype == 15) {
			SeoulVO vo = sService.seoulFestivalDetailData(contentid);
			model.addAttribute("vo",vo);
			jsp = "../seoul/festival.jsp";
		}
		else if(contenttype == 32) {
			SeoulVO vo = sService.seoulAttractionDetailData(contentid);
			model.addAttribute("vo",vo);
			jsp = "../seoul/stey.jsp";
		}
		else if(contenttype == 38) {
			SeoulVO vo = sService.seoulAttractionDetailData(contentid);
			model.addAttribute("vo",vo);
			jsp = "../seoul/shopping.jsp";
		}
		else if(contenttype == 39) {
			SeoulVO vo = sService.seoulAttractionDetailData(contentid);
			model.addAttribute("vo",vo);
			jsp = "../seoul/food_store.jsp";
		}
					
		model.addAttribute("main_jsp", jsp);
		return "main/main";
	}
	
	// 화면 이동 => 데이터 처리(restController)
	@GetMapping("/seoul/find")
	public String seoul_find(Model model) {
		
		model.addAttribute("main_jsp","../seoul/seoul_find.jsp");
		return "main/main";
	}
}
