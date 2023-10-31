package com.example.ssurvey.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "SurveyReply_SEQ_GENERATOR", 
	      sequenceName = "SurveyReply_SEQ",
	      allocationSize = 1)
public class SurveyReply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SurveyReply_SEQ_GENERATOR")
	private Integer srNo;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "surveyNo")
	private Survey survey;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	
	@Column(length = 500)
	private String srContent;
	
	@CreationTimestamp
	private Timestamp srCreateDate;
	

	
}
