package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.booktomusic.dto.RecommendationDto;

@Mapper
public interface RecommendationMapper {

    int insertRecommendation(RecommendationDto recommendationDto);

    RecommendationDto findRecommendationByIdx(@Param("recommendationIdx")int recommendationIdx);
    
    List<RecommendationDto> findRecommendationHistoryByMemberId(@Param("memberId") String memberId);
}