package com.example.ssurvey.domain;

import javax.persistence.Column;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sqNo")
	private SurveyQ surveyQ;
	
	@Column(length = 500)
	private String saAnswer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	

	
}
