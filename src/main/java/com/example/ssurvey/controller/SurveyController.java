package com.example.ssurvey.controller;

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
    
}
