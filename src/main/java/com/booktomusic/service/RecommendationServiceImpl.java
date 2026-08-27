package com.booktomusic.service;

import org.springframework.stereotype.Service;

import com.booktomusic.dto.RecommendationDto;
import com.booktomusic.mapper.RecommendationMapper;
import com.booktomusic.service.RecommendationService;
import java.util.List;

@Service
public class RecommendationServiceImpl
        implements RecommendationService {

    private final RecommendationMapper recommendationMapper;

    public RecommendationServiceImpl(
            RecommendationMapper recommendationMapper) {

        this.recommendationMapper = recommendationMapper;
    }

    @Override
    public RecommendationDto getRecommendation(
            int recommendationIdx) {

        RecommendationDto recommendationDto =
                recommendationMapper.findRecommendationByIdx(
                        recommendationIdx
                );

        if (recommendationDto == null) {
            throw new IllegalStateException(
                    "추천 결과를 찾을 수 없습니다."
            );
        }

        return recommendationDto;
    }
    
    @Override
    public List<RecommendationDto> findRecommendationHistoryByMemberId(String memberId) {
        return recommendationMapper.findRecommendationHistoryByMemberId(memberId);
    }
}