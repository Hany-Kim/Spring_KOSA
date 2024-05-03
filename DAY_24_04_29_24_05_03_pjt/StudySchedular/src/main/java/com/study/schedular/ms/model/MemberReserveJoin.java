package com.study.schedular.ms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberReserveJoin {
	private int joinId;
	private int reserveId; // 외래키
	private int userId; // 외래키
}
