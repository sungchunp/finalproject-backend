package com.example.ssurvey.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "QNA_SEQ_GENERATOR", 
	      sequenceName = "QNA_SEQ",
	      allocationSize = 1)
public class QNA {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QNA_SEQ_GENERATOR")
	private Integer qnaNo;
	
	@Column(nullable = false, length = 100)
	private Integer userNo;
	
	@Column(nullable = false, length = 1000)
	private String qnaQ;
	
	@Column(length = 2000)
	private String qnaA;
	
	@Column(length = 1)
	private int qnaComplete;
	
	@CreationTimestamp
	private Timestamp qnaCreateDate;

	
}
