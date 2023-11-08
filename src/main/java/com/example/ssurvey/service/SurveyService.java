package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.repository.SurveyRepository;

import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public Survey getSurveyById(Integer surveyNo) {
        return surveyRepository.findById(surveyNo).orElse(null);
    }

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Integer surveyNo) {
        surveyRepository.deleteById(surveyNo);
    }
    
    
}
