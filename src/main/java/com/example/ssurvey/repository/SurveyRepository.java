package com.example.ssurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	public Survey findTopByOrderBySurveyNoDesc();
	
}
