package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.booktomusic.dto.RecommendFeedbackDto;

@Mapper
public interface RecommendFeedbackMapper {

    RecommendFeedbackDto findRecommendFeedback(
            @Param("memberId") String memberId,
            @Param("recommendationIdx") int recommendationIdx
    );

    int countRecommendFeedback(
            @Param("memberId") String memberId,
            @Param("recommendationIdx") int recommendationIdx
    );

    int insertRecommendFeedback(RecommendFeedbackDto recommendFeedbackDto);

    int updateRecommendFeedback(RecommendFeedbackDto recommendFeedbackDto);
    
    List<RecommendFeedbackDto> findLikedRecommendationByMemberId(@Param("memberId") String memberId);
}