package com.example.ssurvey.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.dto.FreeBoardDTO;
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
	
	 public Page<FreeBoard> getFreeSearchBoardList(Pageable pageable, String search) {
		 return freeBoardRepository.findByFbTitleContainingIgnoreCase(pageable, search);
	 }
	 
	 
	public FreeBoard getFreeBoard(Integer fbno) {
		return freeBoardRepository.findByFbNo(fbno);
	}
	
	@Transactional
	public void deleteBoard(Integer fbno) {
		freeBoardRepository.deleteByFbNo(fbno);
	}
	
	
	public void updateBoard(Integer fbno, FreeBoardDTO freeBoardDTO) {
		
		FreeBoard oriFreeBoard = getFreeBoard(fbno);
		
		oriFreeBoard.setFbTitle(freeBoardDTO.getFbTitle());
		oriFreeBoard.setFbContent(freeBoardDTO.getFbContent());
		
		freeBoardRepository.save(oriFreeBoard);
	}
	
	public void increaseViews(Integer fbno) {
		
		FreeBoard freeBoard = getFreeBoard(fbno);
		freeBoard.setFbViews(freeBoard.getFbViews() + 1);
		
		freeBoardRepository.save(freeBoard);
		
	}
}

