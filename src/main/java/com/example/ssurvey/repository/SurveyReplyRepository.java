package com.example.ssurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.SurveyReply;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Integer> {

}
