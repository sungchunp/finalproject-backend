package com.example.ssurvey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "FAQ_SEQ_GENERATOR", 
	      sequenceName = "FAQ_SEQ",
	      allocationSize = 1)
public class FAQ {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAQ_SEQ_GENERATOR")
	private Integer faqNo;
	
	@Column(nullable = false, length = 1000)
	private String faqQ;
	
	@Column(length = 2000)
	private String faqA;
	
	@Column(length = 100)
	private String faqCategory;

	
}
