package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.domain.SurveyQ;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.repository.SurveyQRepository;
import com.example.ssurvey.repository.SurveyRepository;
import com.example.ssurvey.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private SurveyQRepository surveyQRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void addSurvey(String surTitle, String surveyCategory, String username) {
		
		User userInfo = (User)userRepository.findByUsername(username).get();
		
		Survey survey = new Survey();
		
		survey.setUser(userInfo);
		survey.setSurTitle(surTitle);
		survey.setSurveyCategory(surveyCategory);
		survey.setSurveyCount(0);
		survey.setSurveyLike(0);
		
		surveyRepository.save(survey);
	}
	
	public void addSurveyQ(List<SurveyQ> surveyQ) {
	 	Integer lastNo = surveyRepository.findTopByOrderBySurveyNoDesc().getSurveyNo();
	 	
	 	for (SurveyQ oneSurveyQ : surveyQ) {
	 		oneSurveyQ.setSurveyNo(lastNo);
	 		surveyQRepository.save(oneSurveyQ);
        }
	}

	public Survey getSurveyById(Integer surveyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Survey> getAllSurveys() {
		// TODO Auto-generated method stub
		return null;
	}
}


