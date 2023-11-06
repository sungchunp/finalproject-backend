package com.example.ssurvey.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.dto.FreeBoardDTO;
import com.example.ssurvey.service.FreeBoardService;
import org.springframework.data.domain.Sort;

@RestController
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/fboard")
	public ResponseEntity<?> insertBoard(@Valid @RequestBody FreeBoardDTO freeBoardDTO, BindingResult bindingResult) {
		
		FreeBoard freeBoard = modelMapper.map(freeBoardDTO, FreeBoard.class);
		
		freeBoardService.insertBoard(freeBoard);
		
		return new ResponseEntity<>("게시글 등록 완료", HttpStatus.OK);
		
	}
	
	@GetMapping("/fboard")
	public Page<FreeBoard> getFreeBoardList(@PageableDefault(sort = "fbNo", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "search") String search) {
		
		if(search == null)
			return freeBoardService.getFreeBoardList(pageable);
        else
           return freeBoardService.getFreeBoardList(pageable, search);
   
	}
	
	
	@GetMapping("/fboard/{fbno}")
	public ResponseEntity<?> getBoard(@PathVariable Integer fbno) {
		
		FreeBoard freeBoard = freeBoardService.getFreeBoard(fbno);
		
		return new ResponseEntity<>(freeBoard, HttpStatus.OK);	
	}
	
	@DeleteMapping("/fboard/{fbno}")
	public ResponseEntity<?> deleteBoard(@PathVariable Integer fbno) {
		
		freeBoardService.deleteBoard(fbno);
		
		return new ResponseEntity<>("게시글 삭제 완료", HttpStatus.OK);
	}
}
