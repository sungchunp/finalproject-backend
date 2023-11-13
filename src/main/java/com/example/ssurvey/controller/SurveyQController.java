package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.service.SurveyQService;

@RestController
public class SurveyQController {

	@Autowired
	private SurveyQService surveyQService;
	
}
