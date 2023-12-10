package com.board.dto;

import java.time.LocalDate;
//import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private String userid;
	private String username;
	private String password;
	private String birthdate;
	private String gender;
	private String telno;
	private String email;
	private LocalDate regdate;
	private LocalDate lastlogindate;
	private LocalDate lastlogoutdate;
	private LocalDate lastpwdate;
	private int pwcheck;
	private String role;
	private String authkey;
	
}
