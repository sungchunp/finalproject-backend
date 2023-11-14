package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.domain.SurveyA;
import com.example.ssurvey.domain.SurveyQ;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.repository.SurveyARepository;
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
	
	@Autowired
	private SurveyARepository surveyARepository;
	
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


	public List<SurveyQ> getSurveyQ(Integer surveyNo) {
			return surveyQRepository.findAllBySurveyNoOrderBySqNoAsc(surveyNo);
		}

	public List<Survey> getAllSurveys() {
			return surveyRepository.findAll();
		}

	public void addAnswer(List<SurveyA> surveyA, String username) {
		User userInfo = (User)userRepository.findByUsername(username).get();
		
		for(SurveyA oneSurveyA : surveyA) {
			oneSurveyA.setUser(userInfo);
			surveyARepository.save(oneSurveyA);
		}
	}
	
	public List<SurveyA> aaa(Integer num) {
		return surveyARepository.findAllBySqNo(num);
	}
	
}


