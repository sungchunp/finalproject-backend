package com.example.ssurvey.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.domain.SurveyReply;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.repository.SurveyReplyRepository;
import com.example.ssurvey.repository.SurveyRepository;

@Service
public class SurveyReplyService {
	@Autowired
	private SurveyReplyRepository surveyReplyRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Transactional
	public void insertSurveyReply(int surveyNo, SurveyReply surveyReply, User user) {
		Survey survey = surveyRepository.findById(surveyNo).get();
		
		surveyReply.setSurvey(survey);
		surveyReply.setUser(user);
		
		surveyReplyRepository.save(surveyReply);
	}
	
	public void deleteSurveyReply(int surveyReplyId) {
		surveyReplyRepository.deleteById(surveyReplyId); 
	}
	
	    
	}