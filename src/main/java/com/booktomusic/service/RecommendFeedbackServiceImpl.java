package com.booktomusic.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.booktomusic.dto.RecommendFeedbackDto;
import com.booktomusic.mapper.RecommendFeedbackMapper;
import com.booktomusic.service.RecommendFeedbackService;

@Service
public class RecommendFeedbackServiceImpl
        implements RecommendFeedbackService {

    private final RecommendFeedbackMapper recommendFeedbackMapper;

    public RecommendFeedbackServiceImpl(
            RecommendFeedbackMapper recommendFeedbackMapper) {

        this.recommendFeedbackMapper = recommendFeedbackMapper;
    }

    @Override
    public RecommendFeedbackDto getRecommendFeedback(
            String memberId,
            int recommendationIdx) {

        int feedbackCount =
                recommendFeedbackMapper.countRecommendFeedback(
                        memberId,
                        recommendationIdx
                );

        if (feedbackCount == 0) {
            return null;
        }

        if (feedbackCount == 1) {
            return recommendFeedbackMapper.findRecommendFeedback(
                    memberId,
                    recommendationIdx
            );
        }

        throw new IllegalStateException(
                "동일한 추천 결과의 피드백 데이터가 중복되었습니다."
        );
    }

    @Override
    public int saveRecommendFeedback(
            RecommendFeedbackDto recommendFeedbackDto) {

        int feedbackCount =
                recommendFeedbackMapper.countRecommendFeedback(
                        recommendFeedbackDto.getMemberId(),
                        recommendFeedbackDto.getRecommendationIdx()
                );

        if (feedbackCount == 0) {
            return recommendFeedbackMapper.insertRecommendFeedback(
                    recommendFeedbackDto
            );
        }

        if (feedbackCount == 1) {
            return recommendFeedbackMapper.updateRecommendFeedback(
                    recommendFeedbackDto
            );
        }

        throw new IllegalStateException(
                "동일한 추천 결과의 피드백 데이터가 중복되었습니다."
        );
    }
    
    @Override
    public List<RecommendFeedbackDto> findLikedRecommendationByMemberId(String memberId) {
        return recommendFeedbackMapper.findLikedRecommendationByMemberId(memberId);
    }
}