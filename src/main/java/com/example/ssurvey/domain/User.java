package com.example.ssurvey.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "User_SEQ_GENERATOR", 
	      sequenceName = "User_SEQ",
	      allocationSize = 1) // 시퀀스 기본 설정이 jpa에서 50씩 증가시켜서 allocationSize 로 1씩증가되도록 수정해둠
@Table(name = "sUser") // user는 오라클 예약어라 사용못함 ( ex : craete user xxx )
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_SEQ_GENERATOR")
	private Integer userNo;
	
	@Column(nullable = false, length = 100, unique = true)
	private String userName;
	
	@Column(nullable = false, length = 50)
	private String userPassword;
	
	@Column(nullable = false, length = 50)
	private String userEmail;
	
	@Enumerated(EnumType.STRING)
	private RoleType userRoletype;
	
	@CreationTimestamp
	private Timestamp userCreatedate;
	
	@Column(length = 300)
	private String userLocation;
	
	@Column(length = 1)
	private int userGender;

	@Column(length = 50)
	private String userJob;
	
	@Column(length = 50)
	private String userAge;
	
	@Column(length = 50)
	private Integer surveyNo;
}

