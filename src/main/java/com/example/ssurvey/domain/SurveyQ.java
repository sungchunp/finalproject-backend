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
	      name = "SurveyQ_SEQ_GENERATOR", 
	      sequenceName = "SurveyQ_SEQ",
	      allocationSize = 1)
public class SurveyQ {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SurveyQ_SEQ_GENERATOR")
	private Integer sqNo;
	
	@Column(length = 1000)
	private Integer surveyNo;
	
	@Column(length = 1000)
	private String sqQuestion;
	
	@Column(length = 10)
	private String sqType;	// 타입 int ->  String으로 변경
	
	@Column(length = 100)
	private String sqFile;

	@Column(length = 100)
	private int id;
	
	@ElementCollection
	@Column(name = "options", length = 1000)
	private List<String> option; // 값 들어오는거 확인하고 문제 있을 시 수정 필요
	
	
}
