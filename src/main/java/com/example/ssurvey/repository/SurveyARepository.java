package com.example.ssurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.SurveyA;

@Repository
public interface SurveyARepository extends JpaRepository<SurveyA, Integer> {

	
	
}
