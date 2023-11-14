package com.example.ssurvey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.FreeBoard;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {
	
	Page<FreeBoard> findAll(Pageable pageable);
	
	public FreeBoard findByFbNo(Integer fbno);

	public void deleteByFbNo(Integer fbno);
	
	public Page<FreeBoard> findByFbTitleContainingIgnoreCase(Pageable pageable, String search);
}
