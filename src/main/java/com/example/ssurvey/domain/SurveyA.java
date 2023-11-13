package com.example.ssurvey.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "SurveyA_SEQ_GENERATOR", 
	      sequenceName = "SurveyA_SEQ",
	      allocationSize = 1)
public class SurveyA {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SurveyA_SEQ_GENERATOR")
	private Integer saNo;
	
	@Column(length = 1000)
	private Integer sqNo;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	

	@ElementCollection
	@Column(name = "answers", length = 1000)
	private List<String> answer; // 값 들어오는거 확인하고 문제 있을 시 수정 필요
}
