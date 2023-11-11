package com.example.ssurvey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.domain.FreeBoardReply;

@Repository
public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Integer> {

	Page<FreeBoardReply> findByFreeBoardFbNo(Integer fbno, Pageable pageable);
	
}
