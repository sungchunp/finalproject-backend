package com.example.ssurvey.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.repository.FreeBoardRepository;

@Service
public class FreeBoardService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Transactional
	public void insertBoard(FreeBoard freeBoard) {
		
		freeBoard.setFbReplyCnt(0);
		freeBoard.setFbViews(0);
		
		freeBoardRepository.save(freeBoard);
		
	}
	
	public List<FreeBoard> getFreeBoardList() {
		return freeBoardRepository.findAllByOrderByFbNoDesc();
	}
	
	public FreeBoard getFreeBoard(Integer fbno) {
		return freeBoardRepository.findById(fbno).get();
	}
	
	public void deleteBoard(Integer fbno) {
		freeBoardRepository.deleteById(fbno);
	}
}

