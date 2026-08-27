package com.booktomusic.service;

import java.util.List;
import com.booktomusic.dto.RecommendFeedbackDto;

public interface RecommendFeedbackService {

    RecommendFeedbackDto getRecommendFeedback(String memberId, int recommendationIdx);

    int saveRecommendFeedback(RecommendFeedbackDto recommendFeedbackDto);
    
    List<RecommendFeedbackDto> findLikedRecommendationByMemberId(String memberId);
}