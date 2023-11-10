package com.example.ssurvey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.domain.SurveyQ;
import com.example.ssurvey.service.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	@PostMapping("/addSurvey")
	public ResponseEntity<?> addSurvey(@RequestBody List<SurveyQ> surveyQ, String surTitle, String surveyCategory, String username) {
		
		surveyService.addSurvey(surTitle, surveyCategory, username);
		
		surveyService.addSurveyQ(surveyQ);
		

		return new ResponseEntity<>("설문 등록 완료", HttpStatus.OK);
	}
	
	
}
