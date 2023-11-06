package com.example.ssurvey.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	 public Page<FreeBoard> getFreeBoardList(Pageable pageable) {
	    return freeBoardRepository.findAll(pageable);
	}
	
	 public Page<FreeBoard> getFreeBoardList(Pageable pageable, String search) {
		 return freeBoardRepository.findByFbTitleContainingIgnoreCase(pageable, search);
	 }
	 
	 
	public FreeBoard getFreeBoard(Integer fbno) {
		return freeBoardRepository.findByFbNo(fbno);
	}
	
	@Transactional
	public void deleteBoard(Integer fbno) {
		freeBoardRepository.deleteByFbNo(fbno);
	}
	
	
}

