package com.booktomusic.service;

import com.booktomusic.dto.RecommendationDto;
import java.util.List;

public interface RecommendationService {

    RecommendationDto getRecommendation(int recommendationIdx);
    
    List<RecommendationDto> findRecommendationHistoryByMemberId(String memberId);
}