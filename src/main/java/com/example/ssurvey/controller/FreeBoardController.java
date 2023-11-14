package com.example.ssurvey.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.domain.FreeBoardReply;
import com.example.ssurvey.dto.FreeBoardDTO;
import com.example.ssurvey.dto.FreeBoardDetailDTO;
import com.example.ssurvey.service.FreeBoardService;

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
	public ResponseEntity<Page<FreeBoard>> getFreeBoardList(@PageableDefault(sort = "fbNo", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "search", required = false)  String search) {
		
		System.out.println(search);
		
		if (search != null && !search.isEmpty()) {
			
			Page<FreeBoard> boardSPage = freeBoardService.getFreeSearchBoardList(pageable, search);
			
	        return new ResponseEntity<>(boardSPage, HttpStatus.OK);
	        
	    } else {
	    	
	    	Page<FreeBoard> boardPage = freeBoardService.getFreeBoardList(pageable);
	    	
	        return new ResponseEntity<>(boardPage, HttpStatus.OK);
	    }
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
	
	@PutMapping("/fboard/{fbno}")
	public ResponseEntity<?> updateBoard(@PathVariable Integer fbno, @Valid @RequestBody FreeBoardDTO freeBoardDTO, BindingResult bindingResult) {
		
		freeBoardService.updateBoard(fbno, freeBoardDTO);
		
		return new ResponseEntity<>("게시글 수정 완료", HttpStatus.OK);
	}
	
	
	@PutMapping("/fboard/view/{fbno}")
	public void increaseViews(@PathVariable Integer fbno) {
		
		freeBoardService.increaseViews(fbno);
	}
	
	@GetMapping("/fboarddetail/{fbno}")
	public ResponseEntity<?> getBoardWithPaging(@PathVariable Integer fbno, @PageableDefault(sort = "fbrNo", direction = Sort.Direction.ASC) Pageable pageable) {
		try {
		// 게시글 상세 정보 가져오기
		FreeBoard freeBoard = freeBoardService.getFreeBoard(fbno);
		
		// 페이징 처리된 댓글 목록 가져오기
		Page<FreeBoardReply> replyPage = freeBoardService.getFreeBoardReplies(fbno, pageable);
		
		// 게시글 정보와 댓글 목록을 DTO에 매핑
		FreeBoardDetailDTO responseDTO = new FreeBoardDetailDTO();
		responseDTO.setFreeBoard(freeBoard);
		responseDTO.setReplyList(replyPage.getContent());
		responseDTO.setTotalPages(replyPage.getTotalPages());
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<>("게시글 및 댓글 정보를 가져오는 중에 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
}
