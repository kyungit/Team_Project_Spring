package com.board.service;


import java.util.HashMap;
import java.util.List;

import com.board.dto.MemberDTO;

public interface MemberService {

	//회원 가입
	public void memberInfoRegistry(MemberDTO member)throws Exception;
	
	//회원 정보 가져 오기
	public MemberDTO memberInfo(String userid)throws Exception;
	
	//아이디 중복 확인
	public int idCheck(String userid)throws Exception;
	
	//패스워드 수정
	public void memberPasswordModify(MemberDTO member)throws Exception;
	
	//마지막 로그인 날짜 등록 하기
	public void lastlogindateUpdate(MemberDTO member)throws Exception;
	
	//마지막 로그아웃 날짜 등록 하기
	public void lastlogoutdateUpdate(MemberDTO member)throws Exception;
	
	//아이디 찾기
	public String searchID(MemberDTO member)throws Exception;
	
	//회원 탈퇴
	public void memberDelete(String userid)throws Exception;
	
	//임시 패스워드 생성
	public String tempPasswordMaker()throws Exception;
	
	//authkey 업데이트
	public void authkeyUpdate(MemberDTO member)throws Exception;
	
	//authkey 존재 여부 확인
	public MemberDTO memberInfoByAuthkey(MemberDTO member)throws Exception;
	
	// 회원 정보 수정
	public void memberInfoUpdate(MemberDTO member);
	
	// 30일 지나면 패스워드 변경 요청
	public List<HashMap<String, Object>> PasswordChangeRequest(String userid);
		
}
