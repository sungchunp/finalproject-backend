package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.domain.FreeBoardReply;
import com.example.ssurvey.repository.FreeBoardReplyRepository;

@Service
public class FreeBoardReplyService {

	@Autowired
	private FreeBoardReplyRepository freeBoardReplyRepository;
	
	public FreeBoardReply saveReply(FreeBoardReply freeBoardReply) {
        return freeBoardReplyRepository.save(freeBoardReply);
    }
	
}
