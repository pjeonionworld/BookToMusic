package com.booktomusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.booktomusic.dto.RecommendationDto;
import com.booktomusic.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {

        this.recommendationService = recommendationService;
    }

    @GetMapping("/{recommendationIdx}")
    public RecommendationDto getRecommendation(
            @PathVariable("recommendationIdx")
            int recommendationIdx) {

        return recommendationService.getRecommendation(
                recommendationIdx
        );
    }
    
    @GetMapping("/history")
    public List<RecommendationDto> getRecommendationHistory(
            @RequestParam("memberId") String memberId) {

        return recommendationService.findRecommendationHistoryByMemberId(memberId);
    }
}