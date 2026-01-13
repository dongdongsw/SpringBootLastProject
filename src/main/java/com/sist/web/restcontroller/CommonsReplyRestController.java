package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.CommonsReplyService;
import com.sist.web.vo.CommonsReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonsReplyRestController {

	private final CommonsReplyService cService;
	public Map CommonsData(int page, int cno) {
		
		Map map = new HashMap<>();
		
		List<CommonsReplyVO> list = cService.commonsReplyListData(cno, (page-1)*10);
		int totalpage = cService.commonsReplyTotalPage(cno);

		final int BLOCK = 5;
		int startPage = ((page-1)/BLOCK*BLOCK) + 1;
		int endPage = ((page-1)/BLOCK*BLOCK) + BLOCK;
		
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("cno", cno);
		map.put("count", list.size());
		
		return map;
	}
	/*
	 * private String[] tbl_name= {"", "seoultravel", "busantravel", "jejutravel"};
	 */

	@GetMapping("/commons/list_vue/")
	public ResponseEntity<Map> commons_list_vue(@RequestParam("page") int page, @RequestParam("cno") int cno){
		Map map = new HashMap<>();
		try {
			
			map = CommonsData(page,cno);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/commons/insert_vue/")
	public ResponseEntity<Map> commons_insert_vue(@RequestBody CommonsReplyVO vo, HttpSession session ){
		
		Map map = new HashMap<>();
		try {
			String id = (String)session.getAttribute("userid");
			String name = (String)session.getAttribute("username");
			String sex = (String)session.getAttribute("sex");
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			
			cService.commonsReplyInsert(vo);
			
			map = CommonsData(vo.getPage(),vo.getCno());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping("/commons/delete_vue/")
	public ResponseEntity<Map> commons_delete_vue(@RequestParam("page") int page, 
			@RequestParam("cno") int cno, @RequestParam("no") int no){
		
		Map map = new HashMap<>();
		try {
			
			cService.commonsDelete(no);
			
			map = CommonsData(page,cno);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(map);
	}
}
