package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.FreeBoardReply;
import com.example.ssurvey.service.FreeBoardReplyService;

@RestController
public class FreeBoardReplyController {

	@Autowired
	private FreeBoardReplyService freeBoardReplyService;
	
	@PostMapping("/freply")
	public void insertReply(@RequestBody FreeBoardReply freeBoardReply) {
		
	}
}
