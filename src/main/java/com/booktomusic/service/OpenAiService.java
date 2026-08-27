package com.booktomusic.service;

import com.booktomusic.dto.OpenAiResponseDto;

public interface OpenAiService {

	OpenAiResponseDto recommendMusic(
	        String bookTitle,
	        String bookAuthor,
	        String bookContents,
	        String mbti,
	        String lyricsPreference
	    );
}