package com.example.ssurvey.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.ssurvey.domain.User;

import lombok.Data;

@Data
public class FreeBoardDTO {

	@NotNull(message = "title을 입력해주세요")
	@NotBlank(message = "title을 입력해주세요")
	private String fbTitle;
	
	
	@NotNull(message = "content를 입력해주세요")
	@NotBlank(message = "content를 입력해주세요")
	private String fbContent;
	
	private String fbFile;
	
	private User user;

}
