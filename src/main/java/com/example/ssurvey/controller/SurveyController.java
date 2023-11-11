package com.example.ssurvey.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.service.SurveyService;

import java.util.List;

@RestController
//@RequestMapping("/survey")
public class SurveyController {

	@Autowired
    private SurveyService surveyService;
 
//    //모든 설문 목록을 가져오는 엔드포인트
//    @GetMapping("/survey")
//    public List<Survey> getAllSurveys() {
//        return surveyService.getAllSurveys();
//    }
    
	@GetMapping("/survey")
	public ResponseEntity<List<Survey>> getAllSurveys() {
	    List<Survey> surveys = surveyService.getAllSurveys();
	    return new ResponseEntity<>(surveys, HttpStatus.OK);
	}
	
	@GetMapping("/surveyNo")
	public ResponseEntity<Survey> getSurveyById(@PathVariable Integer surveyNo) {
	    Survey survey = surveyService.getSurveyById(surveyNo);

	    if (survey != null) {
	        return new ResponseEntity<>(survey, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

//	
//	@GetMapping("/category")
//	public ResponseEntity<List<Survey>> getSurveysByCategory(@RequestParam(name = "category", required = false) String category) {
//	    List<Survey> surveys;
//
//	    if (category != null) {
//	        surveys = surveyService.getSurveysByCategory(category);
//	    } else {
//	        surveys = surveyService.getAllSurveys();
//	    }
//
//	    return new ResponseEntity<>(surveys, HttpStatus.OK);
//	}
    
=======
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
	
	
>>>>>>> df3527166da03aceebff902fd79c989299142f51
}
