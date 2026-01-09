package com.sist.web.restcontroller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.MemberService;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberRestController {

	private final BCryptPasswordEncoder encoder;
	private final MemberService mService;
	
	@GetMapping("/idcheck_vue/")
	public String member_idcheck(@RequestParam("userid") String userid) {
		
		int count = mService.idCheck(userid);
		
		return String.valueOf(count);
	}
	
	@PostMapping("/join_ok")
	public String member_join_ok(@ModelAttribute("vo") MemberVO vo) {
		
		System.out.println(vo);
		
		vo.setPhone(vo.getPhone1() + "-" + vo.getPhone2());
		vo.setUserpwd(encoder.encode(vo.getUserpwd()));
		
		mService.memberInsert(vo);
		mService.memberAuthorityInsert(vo.getUserid());
		
		return "redirect:/main";
	}
	
	@RequestMapping("/login")
	public String member_login(Model model) {
		
		
		model.addAttribute("main_jsp", "../member/login.jsp");
		return "main/main";
	}
}
