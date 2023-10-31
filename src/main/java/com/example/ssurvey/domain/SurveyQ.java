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
	      name = "SurveyQ_SEQ_GENERATOR", 
	      sequenceName = "SurveyQ_SEQ",
	      allocationSize = 1)
public class SurveyQ {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SurveyQ_SEQ_GENERATOR")
	private Integer sqNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "surveyNo")
	private Survey survey;
	
	@Column(length = 1000)
	private String sqQuestion;
	
	@Column(length = 10)
	private int sqType;
	
	@Column(length = 100)
	private String sqFile;

	
}
