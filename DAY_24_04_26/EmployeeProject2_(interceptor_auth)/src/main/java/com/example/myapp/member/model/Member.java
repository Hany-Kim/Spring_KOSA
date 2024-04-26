package com.example.myapp.member.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class Member {
//	테이블 정보
//	USERID VARCHAR2(50) PRIMARY KEY,
//  NAME VARCHAR2(50) NOT NULL,
//  PASSWORD VARCHAR2(150) NOT NULL,
//  EMAIL VARCHAR2(100) NOT NULL,
//  PHONE VARCHAR2(50),
//  ROLE VARCHAR2(20) DEFAULT 'ROLE_USER'
	
	private String userid;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String role;
}
