package com.example.ssurvey.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "FreeBoard_SEQ_GENERATOR", 
	      sequenceName = "FreeBoard_SEQ",
	      allocationSize = 1)
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FreeBoard_SEQ_GENERATOR")
	private Integer fbNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	
	@Column(length = 100)
	private String fbFile;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private String fbCreateBoard;
	
	@Column(length = 10)
	private int fbViews;
	
	@Column(length = 5000)
	private String fbContent;

	@Column(length = 10)
	private int fbReplyCnt;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "freeBoard",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("fbrNo desc")
	private List<FreeBoardReply> fbReplyList;
	
	@Column(length = 50)
	private String fbTitle;
	
	 @PrePersist
	    protected void onCreate() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        fbCreateBoard = dateFormat.format(new Date());
	    }
}
