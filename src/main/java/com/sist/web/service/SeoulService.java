package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.SeoulVO;

/*			 /seoul/location
 * 					| 인가
 * 	user요청 -------------------- DispatcherServlet
 * 										| 위임
 * 								HandlerMapping
 * 										| URI 주소 찾기 =>  	@GetMapping
 * 														 	@PostMapping
 * 														 	@PutMapping
 * 														 	@DeleteMapping
 * 											=> 인증 => 권한부여
 * 										| 밑에 있는 메소드 호출
 * 												| 
 * 											Service				 	
 * 												|
 * 											Mapper => 수정시에 의존성을 약하게 만든다
 * 												|
 * 											  오라클
 * 										DispatcherServlet
 * 											|
 * 										ViewResolver
 * 											|
 * 											JSP
 * 
 */

public interface SeoulService {
	// 일반 리스트 
	public List<SeoulVO> seoulListData(Map map);
	public int seoulLocationTotalPage(int contenttype);
	
	// 디테일
	public SeoulVO seoulAttractionDetailData(int contentid);
	
	// 검색
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	
	// top4 (메인페이지)
	public List<SeoulVO> seoulTop5Data();
}
