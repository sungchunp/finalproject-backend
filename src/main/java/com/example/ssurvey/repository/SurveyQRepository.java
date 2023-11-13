package com.example.ssurvey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.SurveyQ;

@Repository
public interface SurveyQRepository extends JpaRepository<SurveyQ, Integer> {

	
}
