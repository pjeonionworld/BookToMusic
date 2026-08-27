package com.booktomusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booktomusic.dto.BookDto;
import com.booktomusic.dto.KakaoBookResponseDto;
import com.booktomusic.dto.RecommendationDto;
import com.booktomusic.service.BookService;
import com.booktomusic.service.KakaoBookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final KakaoBookService kakaoBookService;

    public BookController(
            BookService bookService,
            KakaoBookService kakaoBookService) {

        this.bookService = bookService;
        this.kakaoBookService = kakaoBookService;
    }

    @PostMapping
    public RecommendationDto saveBook(
            @RequestBody BookDto bookDto,
            @RequestParam("memberId") String memberId) {

        return bookService.saveBook(
                bookDto,
                memberId
        );
    }

    @GetMapping("/search")
    public KakaoBookResponseDto searchBook(
            @RequestParam("query") String query) {

        return kakaoBookService.searchBook(query);
    }
}