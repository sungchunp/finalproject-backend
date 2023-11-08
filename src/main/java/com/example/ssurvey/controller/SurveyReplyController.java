package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ssurvey.service.SurveyReplyService;

@Controller
public class SurveyReplyController {

	@Autowired
	private SurveyReplyService surveyReplyService;
	
}
