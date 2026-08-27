package com.booktomusic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.booktomusic.dto.RecommendFeedbackDto;
import com.booktomusic.service.RecommendFeedbackService;

@RestController
@RequestMapping("/api/recommend-feedback")
public class RecommendFeedbackController {

    private final RecommendFeedbackService recommendFeedbackService;

    public RecommendFeedbackController(
            RecommendFeedbackService recommendFeedbackService) {

        this.recommendFeedbackService = recommendFeedbackService;
    }

    @GetMapping
    public ResponseEntity<RecommendFeedbackDto> getRecommendFeedback(
            @RequestParam("recommendationIdx") int recommendationIdx,
            Authentication authentication) {

        if (authentication == null) {
            RecommendFeedbackDto recommendFeedbackDto =
                    new RecommendFeedbackDto();

            recommendFeedbackDto.setRecommendationIdx(
                    recommendationIdx
            );
            recommendFeedbackDto.setLikeYn(0);
            recommendFeedbackDto.setDislikeYn(0);

            return ResponseEntity.ok(recommendFeedbackDto);
        }

        String memberId = authentication.getName();

        RecommendFeedbackDto recommendFeedbackDto =
                recommendFeedbackService.getRecommendFeedback(
                        memberId,
                        recommendationIdx
                );

        if (recommendFeedbackDto == null) {
            RecommendFeedbackDto emptyFeedbackDto =
                    new RecommendFeedbackDto();

            emptyFeedbackDto.setMemberId(memberId);
            emptyFeedbackDto.setRecommendationIdx(
                    recommendationIdx
            );
            emptyFeedbackDto.setLikeYn(0);
            emptyFeedbackDto.setDislikeYn(0);

            return ResponseEntity.ok(emptyFeedbackDto);
        }

        return ResponseEntity.ok(recommendFeedbackDto);
    }

    @PostMapping
    public ResponseEntity<String> saveRecommendFeedback(
            @RequestBody RecommendFeedbackDto recommendFeedbackDto,
            Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("로그인 후 이용할 수 있습니다.");
        }

        String memberId = authentication.getName();

        recommendFeedbackDto.setMemberId(memberId);

        recommendFeedbackService.saveRecommendFeedback(
                recommendFeedbackDto
        );

        return ResponseEntity.ok("저장 완료");
    }
    
    @GetMapping("/likes")
    public List<RecommendFeedbackDto> getLikedRecommendationList(
            @RequestParam("memberId") String memberId) {

        return recommendFeedbackService.findLikedRecommendationByMemberId(memberId);
    }
}