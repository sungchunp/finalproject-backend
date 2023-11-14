package com.example.ssurvey.dto;

import java.util.List;

import com.example.ssurvey.domain.FreeBoard;
import com.example.ssurvey.domain.FreeBoardReply;

import lombok.Data;

@Data
public class FreeBoardDetailDTO {

	private FreeBoard freeBoard;
    private List<FreeBoardReply> replyList;
    private int totalPages;

	
}
