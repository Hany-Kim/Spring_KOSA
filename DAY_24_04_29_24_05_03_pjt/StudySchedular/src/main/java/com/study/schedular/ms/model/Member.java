package com.study.schedular.ms.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Member {
	private int userId;
	private String name;
	private String phone;
	private String profile;
	private String password;
}
