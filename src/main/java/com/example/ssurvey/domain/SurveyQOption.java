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
	      name = "SurveyQOption_SEQ_GENERATOR", 
	      sequenceName = "SurveyQOption_SEQ",
	      allocationSize = 1)
public class SurveyQOption {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SurveyQOption_SEQ_GENERATOR")
	private Integer sqoNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sqNo")
	private SurveyQ surveyQ;
	
	@Column(length = 300)
	private String sqoOption;
	
	@Column(length = 100)
	private String sqoFile;

	
}
