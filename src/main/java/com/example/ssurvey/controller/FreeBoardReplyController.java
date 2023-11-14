package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.domain.FreeBoardReply;
import com.example.ssurvey.service.FreeBoardReplyService;

@RestController
public class FreeBoardReplyController {

	@Autowired
	private FreeBoardReplyService freeBoardReplyService;
	
	@PostMapping("/replies")
    public ResponseEntity<FreeBoardReply> saveReply(@RequestBody FreeBoardReply freeBoardReply) {
       
		FreeBoardReply savedReply = freeBoardReplyService.saveReply(freeBoardReply);
        
        return new ResponseEntity<>(savedReply, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/replies/{fbrNo}")
	public void deleteReply(@PathVariable Integer fbrNo) {
		freeBoardReplyService.deleteReply(fbrNo);
	}
}
