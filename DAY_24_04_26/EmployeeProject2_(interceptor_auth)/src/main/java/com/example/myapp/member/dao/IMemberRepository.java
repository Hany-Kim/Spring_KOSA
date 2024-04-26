package com.example.myapp.member.dao;

public interface IMemberRepository {
	String getPassword(String userid);
	
	String getEmail(String userid);
}
