package com.example.ssurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.FreeBoardReply;

@Repository
public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Integer> {

}
