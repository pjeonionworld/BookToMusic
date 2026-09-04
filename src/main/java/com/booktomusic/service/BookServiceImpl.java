package com.booktomusic.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booktomusic.dto.BookDto;
import com.booktomusic.dto.KakaoBookDto;
import com.booktomusic.dto.KakaoBookResponseDto;
import com.booktomusic.dto.RecommendationDto;
import com.booktomusic.dto.MemberDto;
import com.booktomusic.dto.MemberDto;
import com.booktomusic.dto.MusicDto;
import com.booktomusic.dto.OpenAiResponseDto;
import com.booktomusic.mapper.BookMapper;
import com.booktomusic.mapper.RecommendationMapper;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final RecommendationMapper recommendationMapper;
    private final KakaoBookService kakaoBookService;
    private final MemberService memberService;
    private final OpenAiService openAiService;
    private final SpotifyService spotifyService;
    private final MusicService musicService;

    public BookServiceImpl(
            BookMapper bookMapper,
            RecommendationMapper recommendationMapper,
            KakaoBookService kakaoBookService,
            MemberService memberService,
            OpenAiService openAiService,
            SpotifyService spotifyService,
            MusicService musicService) {

        this.bookMapper = bookMapper;
        this.recommendationMapper = recommendationMapper;
        this.kakaoBookService = kakaoBookService;
        this.memberService = memberService;
        this.openAiService = openAiService;
        this.spotifyService = spotifyService;
        this.musicService = musicService;;
    }

    @Override
    @Transactional
    public RecommendationDto saveBook(BookDto requestBookDto, String memberId) {
    	
    	BookDto savedBookDto =findOrSaveBook(requestBookDto);
    	
        String mbti = null;
        String lyricsPreference = null;

        if (!"visit".equals(memberId)) {
            MemberDto memberDetailDto = memberService.getMemberDetail(memberId);

            if (memberDetailDto != null) {
                mbti = memberDetailDto.getMbti();
                lyricsPreference = memberDetailDto.getLyricsPreference();
            }
        }
        	
        OpenAiResponseDto openAiResponseDto = openAiService.recommendMusic(
        	    savedBookDto.getBookTitle(),
        	    savedBookDto.getBookAuthor(),
        	    savedBookDto.getBookContents(),
        	    mbti,
        	    lyricsPreference
        	);

        MusicDto musicDto = spotifyService.searchMusic(
        	    openAiResponseDto.getMusicTitle(),
        	    openAiResponseDto.getMusicArtist()
        	);

        int musicIdx = musicService.saveMusic(musicDto);
        
        RecommendationDto recommendationDto = new RecommendationDto();

        recommendationDto.setMemberId(memberId);
        recommendationDto.setBookIdx(savedBookDto.getBookIdx());
        recommendationDto.setMusicIdx(musicIdx);

        int recommendationInsertResult = recommendationMapper.insertRecommendation(recommendationDto);
        
        if (recommendationInsertResult != 1) {
            throw new IllegalStateException("추천 결과 저장에 실패했습니다.");
        }
        
        return recommendationDto;
    }
    
    
    @Override
    public BookDto findOrSaveBook(BookDto requestBookDto) {

        String searchQuery = requestBookDto.getBookTitle().trim();

        if (requestBookDto.getBookAuthor() != null
                && !requestBookDto.getBookAuthor().isBlank()) {

            searchQuery += " " + requestBookDto.getBookAuthor().trim();
        }

        KakaoBookResponseDto kakaoBookResponseDto =kakaoBookService.searchBook(searchQuery);

        if (kakaoBookResponseDto == null
                || kakaoBookResponseDto.getDocuments() == null
                || kakaoBookResponseDto.getDocuments().isEmpty()) {

            throw new IllegalArgumentException(
                    "검색된 책이 없습니다."
            );
        }

        KakaoBookDto kakaoBookDto =kakaoBookResponseDto.getDocuments().get(0);

        String isbn =extractIsbn(kakaoBookDto.getIsbn());

        BookDto savedBookDto =bookMapper.findBookByIsbn(isbn);

        if (savedBookDto != null) {
            return savedBookDto;
        }

        savedBookDto =convertToBookDto(kakaoBookDto, isbn);

        int bookInsertResult =bookMapper.insertBook(savedBookDto);

        if (bookInsertResult != 1) {
            throw new IllegalStateException(
                    "책 저장에 실패했습니다."
            );
        }

        return savedBookDto;
    }
    
    
    

    private BookDto convertToBookDto(KakaoBookDto kakaoBookDto, String isbn) {

        BookDto bookDto = new BookDto();

        bookDto.setIsbn(isbn);
        bookDto.setBookTitle(kakaoBookDto.getTitle());
        bookDto.setBookAuthor(joinAuthors(kakaoBookDto.getAuthors()));
        bookDto.setPublisher(kakaoBookDto.getPublisher());
        bookDto.setBookThumbnail(kakaoBookDto.getThumbnail());
        bookDto.setBookContents(kakaoBookDto.getContents());

        if (kakaoBookDto.getPublishedDate() != null
                && !kakaoBookDto.getPublishedDate().isBlank()) {

            bookDto.setPublishedDate(
                    OffsetDateTime.parse(kakaoBookDto.getPublishedDate())
                            .toLocalDate());
        }

        return bookDto;
    }

    private String joinAuthors(List<String> authors) {

        if (authors == null || authors.isEmpty()) {
            return "";
        }

        return String.join(", ", authors);
    }

    private String extractIsbn(String isbnValue) {

        if (isbnValue == null || isbnValue.isBlank()) {
            throw new IllegalArgumentException("ISBN 정보가 없는 책입니다.");
        }

        String[] isbnList = isbnValue.trim().split("\\s+");

        for (String isbn : isbnList) {
            if (isbn.length() == 13) {
                return isbn;
            }
        }

        return isbnList[0];
    }
}