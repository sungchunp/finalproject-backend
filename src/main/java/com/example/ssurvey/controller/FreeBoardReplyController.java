package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		freeBoardReplyService.insertReply(freeBoardReply);
	}
	
	@GetMapping("/freply/{fbno}")
	public ResponseEntity<?> getReplyList(@PathVariable Integer fbno) {
		
	}
}
