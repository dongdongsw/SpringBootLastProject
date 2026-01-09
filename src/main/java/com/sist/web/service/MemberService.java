package com.sist.web.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.MemberVO;

public interface MemberService {

//	@Select("SELECT COUNT(*) FROM project_member_1 "
//			+ "WHERE userid = #{userid}")
	public int idCheck(String userid);
	
//	@Insert("INSERT INTO project_member_1(userid, username, userpwd, "
//			+ "sex, birthday, email, post, addr1, addr2, phone, content) "
//			+ "VALUES(#{userid}, #{username}, #{userpwd}, #{sex}, #{birthday}, "
//			+ "#{email}, #{post}, #{addr1}, #{addr2}, #{phone}, #{content})")
	public void memberInsert(MemberVO vo);
	
	public void memberAuthorityInsert(String userid);
	
	public MemberVO memberInfoData(String userid);
}
