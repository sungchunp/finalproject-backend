package com.example.ssurvey.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
    public void deleteReply(Integer fbrNo) {
        try {
            FreeBoardReply replyToDelete = freeBoardReplyRepository.findByFbrNo(fbrNo);
            if (replyToDelete != null) {
                // 댓글 엔터티에서 FreeBoard 참조 제거
                replyToDelete.setFreeBoard(null);
                freeBoardReplyRepository.delete(replyToDelete);
            } else {
                throw new EntityNotFoundException("댓글을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            // 예외 발생 시 롤백
            throw new RuntimeException("댓글 삭제 중 오류가 발생했습니다.", e);
        }
    }
}
