package com.example.ssurvey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.repository.SurveyRepository;

@RestController
public class SurveyController {
	
	 private final SurveyRepository surveyRepository;

	    @Autowired
	    public SurveyController(SurveyRepository surveyRepository) {
	        this.surveyRepository = surveyRepository;
	    }

	    @GetMapping
	    public List<Survey> getAllSurveys() {
	        return (List<Survey>) surveyRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Survey getSurveyById(@PathVariable Integer id) {
	        return surveyRepository.findById(id).orElse(null);
	    }

	    @PostMapping
	    public Survey createSurvey(@RequestBody Survey survey) {
	        return surveyRepository.save(survey);
	    }

	    @PutMapping("/{id}")
	    public Survey updateSurvey(@PathVariable Integer id, @RequestBody Survey updatedSurvey) {
	        updatedSurvey.setSurveyNo(id);
	        return surveyRepository.save(updatedSurvey);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteSurvey(@PathVariable Integer id) {
	        surveyRepository.deleteById(id);
	    }
	
}
