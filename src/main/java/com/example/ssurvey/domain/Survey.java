package com.example.ssurvey.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "Survey_SEQ_GENERATOR", 
	      sequenceName = "Survey_SEQ",
	      allocationSize = 1)
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Survey_SEQ_GENERATOR")
	private Integer surveyNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	
	@Column(length = 200)
	private String surveyCategory;
	
	@CreationTimestamp
	private Timestamp surveyCreateDate;
	
	private LocalDate surveyStart;
	
	private LocalDate surveyEnd;
	
	@Column(length = 200)
	private int surveyLike;
	
	@Column(length = 3000)
	private String surveyContent;
	
	@Column(length = 200)
	private int surveyCount;

	@OneToMany(mappedBy = "survey",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("fbrCreateDate desc") //댓글 작성 일을 기준으로 내림차순
	private List<SurveyReply> surveyReplyList;
}



