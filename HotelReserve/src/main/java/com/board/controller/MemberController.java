package com.board.controller;

import java.net.URLEncoder;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.board.dto.AddressDTO;
import com.board.dto.MemberDTO;
import com.board.service.MemberService;
//import com.board.util.Page;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	//로그인 화면 보기
	@GetMapping("/member/login")
	public void getLogin() { }
	
	//로그인 
	@ResponseBody
	@PostMapping("/member/login")
	public String postLogin(MemberDTO member,HttpSession session,@RequestParam("autologin") String autologin) throws Exception {
		
		String authkey = "";
		
		//로그인 시 authkey 생성  
		if(autologin.equals("NEW")) {
			authkey = UUID.randomUUID().toString().replaceAll("-", "");
			member.setAuthkey(authkey);
			service.authkeyUpdate(member);			
		}
		
		//authkey가 클라이언트에 쿠키로 존재할 경우 로그인 과정 없이 세션 생성 후 게시판 목록 페이지로 이동
		if(autologin.equals("PASS")) {
			MemberDTO memberInfo = service.memberInfoByAuthkey(member);
			if(memberInfo != null) {
				//세션 생성
				session.setMaxInactiveInterval(3600*24*7);//세션 유지 기간 설정
				session.setAttribute("userid", memberInfo.getUserid());
				session.setAttribute("username", memberInfo.getUsername());
				session.setAttribute("role", memberInfo.getRole());
				
				return "{\"message\":\"GOOD\"}";
			}
		}		
		
		//아이디 존재 여부 확인
		if(service.idCheck(member.getUserid()) == 0) {
			return "{\"message\":\"ID_NOT_FOUND\"}";
		}
		
		//패스워드가 올바르게 들어 왔는지 확인
		if(!pwdEncoder.matches(member.getPassword(), service.memberInfo(member.getUserid()).getPassword())) {
			
			//잘못된 패스워드 일때
			return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
				}else {
			//제대로 된 아이디와 패스워드가 입력되었을 때
			
			//마지막 로그인 날짜 등록
			member.setLastlogindate(LocalDate.now());
			service.lastlogindateUpdate(member);	
			
			//패스워드 확인 후 마지막 패스워드 변경일이 30일이 경과 되었을 경우 ...
			
			//세션 생성
			session.setMaxInactiveInterval(3600*24*7);//세션 유지 기간 설정
			session.setAttribute("userid", service.memberInfo(member.getUserid()).getUserid());
			session.setAttribute("username", service.memberInfo(member.getUserid()).getUsername());
			session.setAttribute("role", service.memberInfo(member.getUserid()).getRole());
			
			return "{\"message\":\"GOOD\",\"authkey\":\"" + member.getAuthkey() + "\"}";
	        }
		}
	
	
	//회원 등록 화면 보기
	@GetMapping("/member/signup")
	public void getSignup() {	}
	
	//회원 등록 하기
	@ResponseBody
	@PostMapping("/member/signup")
	public Map<String,String> postSignup(MemberDTO member) throws Exception {
			
			String inputPassword = member.getPassword();
			String pwd = pwdEncoder.encode(inputPassword); //단방향 암호화 
			member.setPassword(pwd);
			member.setLastpwdate(LocalDate.now());
		
		service.memberInfoRegistry(member);
		
		Map<String,String> data = new HashMap<>();
		data.put("message", "GOOD");
		data.put("username", URLEncoder.encode(member.getUsername(),"UTF-8"));
		
		return data;
		
		//return "{\"message\":\"GOOD\",\"username\":\"" + URLEncoder.encode(member.getUsername(),"UTF-8") + "\"}";
	}
	
	//회원 가입 시 아이디 중복 확인
	@ResponseBody
	@PostMapping("/member/idCheck")
	public int postIdCheck(@RequestBody String userid) throws Exception{
		
		int result = service.idCheck(userid);
		return result;
	}
	
	//회원 정보 보기
	@GetMapping("/member/memberInfo")
	public void getMemberInfo(HttpSession session, Model model) throws Exception {
		String userid = (String)session.getAttribute("userid");
		model.addAttribute("memberInfo", service.memberInfo(userid));
	}
	
	//회원 기본 정보 변경
	@GetMapping("/member/memberInfoModify")
	public void getMembetInfoModify() {
		
	}
	
	//회원 패스워드 변경
	@GetMapping("/member/memberPasswordModify")
	public void getMemberPasswordModify() throws Exception { }
	
	//회원 패스워드 변경
	@ResponseBody
	@PostMapping("/member/memberPasswordModify")
	public String postMemberPasswordModify(@RequestParam("old_password") String old_password, 
				@RequestParam("new_password") String new_password, HttpSession session) throws Exception { 
		
		String userid = (String)session.getAttribute("userid");		
		
		//패스워드가 올바르게 들어 왔는지 확인
		if(!pwdEncoder.matches(old_password, service.memberInfo(userid).getPassword())) {
			return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
		}
		
		//신규 패스워드로 업데이트
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);		
		member.setPassword(pwdEncoder.encode(new_password));
		member.setLastpwdate(LocalDate.now());
		service.memberPasswordModify(member);
		
		return "{\"message\":\"GOOD\"}";
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public void getLogout(HttpSession session,Model model) throws Exception {
		String userid = (String)session.getAttribute("userid");
		String username = (String)session.getAttribute("username");
		
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);
		member.setLastlogoutdate(LocalDate.now());
		
		service.lastlogoutdateUpdate(member);
		
		model.addAttribute("userid", userid);
		model.addAttribute("username", username);
		session.invalidate(); //모든 세션 종료 --> 로그아웃...
	}
	
	//패스워드 변경 후 세션 종료
	@GetMapping("/member/memberSessionOut")
	public String getMemberSessionOut(HttpSession session) throws Exception {
		
		MemberDTO member = new MemberDTO();
		member.setUserid((String)session.getAttribute("userid"));
		member.setLastlogoutdate(LocalDate.now());
		service.lastlogoutdateUpdate(member);
		session.invalidate();	
		
		return "redirect:/";
	}
	
	//아이디 찾기
	@GetMapping("/member/searchID")
	public void getSearchID() {}
	
	//아이디 찾기
	@ResponseBody
	@PostMapping("/member/searchID")
	public String postSearchID(MemberDTO member) throws Exception {
		
		String userid = service.searchID(member) == null?"ID_NOT_FOUND":service.searchID(member);		
		return "{\"message\":\"" + userid + "\"}";
	}
	
	//임시 패스워드 생성
	@GetMapping("/member/searchPassword")
	public void getSearchPassword() {}
	
	//임시 패스워드 생성
	@ResponseBody
	@PostMapping("/member/searchPassword")
	public String postSearchPassword(MemberDTO member) throws Exception{
		//아이디 존재 여부 확인
		if(service.idCheck(member.getUserid()) == 0)
			return "{\"status\":\"ID_NOT_FOUND\"}";
		//TELNO 확인
		if(!service.memberInfo(member.getUserid()).getTelno().equals(member.getTelno()))
			return "{\"status\":\"TELNO_NOT_FOUND\"}";
		
		//임시 패스워드 생성	
		String rawTempPW = service.tempPasswordMaker();
		member.setPassword(pwdEncoder.encode(rawTempPW));
		member.setLastpwdate(LocalDate.now());
		service.memberPasswordModify(member);

		return "{\"status\":\"GOOD\",\"password\":\"" + rawTempPW + "\"}";
	}
	
	//로그인 시 패스워드 변경 여부 확인
	@GetMapping("/member/pwCheckNotice")
	public void getPWCheckNotice() throws Exception {
		
	}

	//회원 탈퇴
	@Transactional
	@GetMapping("/member/memberDelete")
	public void getMemberDelete(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		String username = (String)session.getAttribute("username");
		MemberDTO member = new MemberDTO();
		
		member.setUserid(userid);
		member.setUsername(username);
		
		model.addAttribute("userid", userid);
		model.addAttribute("username", username);
	}
	@ResponseBody
	@PostMapping("/member/memberDelete")
	public String postMemberDelete(@RequestParam("userid")String userid, HttpSession session) throws Exception {
		service.memberDelete(userid);
		session.invalidate();
		
		int i = service.idCheck(userid);
		if(i == 0) {
			return "{\"message\" : \"GOOD\"}";
		}else {
			return "{\"message\" : \"BAD\"}";
		}
	}
	
	// 기본 정보 수정
	@ResponseBody
	@PostMapping("/member/memberInfoModify")
	public Map<String, String> postMemberInfoUpdate(HttpSession session, MemberDTO member, @RequestParam("fileUpload") MultipartFile multipartFile) throws Exception {

		String userid = (String)session.getAttribute("userid");
		String username = (String)session.getAttribute("username");

			member.setUserid(userid);
			service.memberInfoUpdate(member);
			
			Map<String,String> data = new HashMap<>(); data.put("message", "GOOD");
			data.put("username", URLEncoder.encode(username, "UTF-8"));
			 
			return data;	
	}
}
