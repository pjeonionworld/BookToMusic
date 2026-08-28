package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.booktomusic.dto.RankingDto;

@Mapper
public interface RankingMapper {

    RankingDto selectTopBookByMbti(@Param("mbti") String mbti);
    
    int insertDailyMbtiRanking(RankingDto rankingDto);

    RankingDto selectTodayMbtiRanking();
    
    List<RankingDto> selectBookViewRanking();

    List<RankingDto> selectMusicViewRanking();

    List<RankingDto> selectRecommendationLikeRanking();
    
    List<RankingDto> selectMbtiRanking(@Param("mbti") String mbti);
    
}