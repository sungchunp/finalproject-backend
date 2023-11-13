package com.example.ssurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssurvey.repository.SurveyQRepository;

@Service
public class SurveyQService {

	@Autowired
	private SurveyQRepository surveyQRepository;
	
}
