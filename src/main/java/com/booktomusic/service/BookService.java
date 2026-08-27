package com.booktomusic.service;

import com.booktomusic.dto.BookDto;
import com.booktomusic.dto.RecommendationDto;

public interface BookService{
	
	BookDto findOrSaveBook(BookDto requestBookDto);
	
	RecommendationDto saveBook(BookDto bookDto, String memberId);
	
}