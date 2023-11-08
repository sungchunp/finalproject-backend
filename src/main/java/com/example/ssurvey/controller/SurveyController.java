package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    
    // 모든 설문 목록을 가져오는 엔드포인트
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{surveyNo}")
    public Survey getSurveyById(@PathVariable Integer surveyNo) {
        return surveyService.getSurveyById(surveyNo);
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @DeleteMapping("/{surveyNo}")
    public void deleteSurvey(@PathVariable Integer surveyNo) {
        surveyService.deleteSurvey(surveyNo);
    }
    
    
}
