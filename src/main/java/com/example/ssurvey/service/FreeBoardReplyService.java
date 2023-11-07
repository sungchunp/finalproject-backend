package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.FreeBoardReply;
import com.example.ssurvey.repository.FreeBoardReplyRepository;

@Service
public class FreeBoardReplyService {

	@Autowired
	private FreeBoardReplyRepository freeBoardReplyRepository;
	
	public void insertReply(FreeBoardReply freeBoardReply) {
		freeBoardReplyRepository.save(freeBoardReply);
	}
}
