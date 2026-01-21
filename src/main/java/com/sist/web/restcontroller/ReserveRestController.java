package com.sist.web.restcontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReserveRestController {

	private final ReserveService rService;
	
	private final SimpMessagingTemplate template;
	
	private final String[] times = {
			"09:00","10:00","11:00","12:00","12:30","13:00","13:30","14:00","15:00","16:00","17:00","18:00","18:30",
			"19:00","20:00","20:30","21:00","22:00"
	};
	public List<String> timeRand() {
		
		List<String> res = new ArrayList<>();
		
		int[] com = new int[(int)(Math.random()*6)+5];
		boolean bClick = false;
		int su = 0;
		
		for(int i = 0; i< com.length; i++) {
			
			bClick = true;
			
			while(bClick) {
			
				su = (int)(Math.random()*18);
				bClick = false;
				
				for(int j = 0; j < i; j++) {
				
					if(com[j] == su) {
					
						bClick = true;
						
						break;
					}
				}
			}
			com[i] = su;
		}
		Arrays.sort(com);
		for(int i = 0; i< com.length; i++) {
			res.add(times[com[i]]);
			
		}
		
		return res;
	}
	
	@GetMapping("/reserve/food_list_vue/")
	public ResponseEntity<Map> reserve_food_list_vue(@RequestParam("page") int page, 
			@RequestParam("address") String address){
		
		Map map = new HashMap<>();
		try {
			
			map.put("address", address);
			map.put("start", (page-1)*10);
			List<SeoulVO> list = rService.seoulReserveData(map);
			int totalpage = rService.seoulReserveTotalPage(address);
			
			map = new HashMap<>();
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
			map.put("address", address);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/reserve/time_list_vue/")
	public ResponseEntity<Map> reserve_time_list_vue(){

		Map map = new HashMap<>();
		
		try {
			List<String> list = timeRand();
			map.put("list", list);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/reserve/insert-vue/")
	public ResponseEntity<String> reserve_insert(@RequestBody ReserveVO vo, HttpSession session){
		
		String res = "";
		try {
			
			String id = (String)session.getAttribute("userid");
			vo.setId(id);
			res = rService.reserveInsert(vo);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/mypage/reserve_list_vue/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_list_vue(HttpSession session){
		
		List<ReserveVO> list = new ArrayList<>();
		try {
			String id = (String)session.getAttribute("userid");
			list = rService.reserveMyData(id);
			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/admin/reserve_list_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_list_vue(){
		
		List<ReserveVO> list = new ArrayList<>();
		try {
			
			list = rService.reserveAdminData();
			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/admin/reserve_ok_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_ok(@RequestParam("no") int no, @RequestParam("id") String id){
		
		List<ReserveVO> list = new ArrayList<>();
		
		try {
			rService.reserveOk(no);
			list = rService.reserveAdminData();
			
			template.convertAndSend(
					"/sub/notice/" + id, 
					"[예약 승인] 예약 완료"
			);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/mypage/reserve_cancel_vue/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_cancel(@RequestParam("no") int no, HttpSession session){
		
		List<ReserveVO> list = new ArrayList<>();
		
		try {
			rService.reserveCancel(no);
			String id = (String)session.getAttribute("userid");
			list = rService.reserveMyData(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/admin/reserve_delete_vue/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_delete(@RequestParam("no") int no,@RequestParam("id") String id){
		
		List<ReserveVO> list = new ArrayList<>();
		
		try {
			rService.reserveDelete(no);
			list = rService.reserveAdminData();
			
			template.convertAndSend(
					"/sub/notice/" + id, 
					"[예약 취소] 예약 취소 완료"
			);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/mypage/reserve_detail_vue/")
	public ResponseEntity<ReserveVO> mypage_reserve_detail(@RequestParam("no") int no){
		
		ReserveVO vo =  new ReserveVO();
		
		try {
			vo = rService.reserveDetailData(no);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	

}
