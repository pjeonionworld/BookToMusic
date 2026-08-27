package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.booktomusic.dto.RankingDto;

@Mapper
public interface RankingMapper {

    RankingDto selectTopBookByMbti(
            @Param("mbti") String mbti
    );
    
    int insertDailyMbtiRanking(RankingDto rankingDto);

    RankingDto selectTodayMbtiRanking();
    
    // 책 조회 랭킹
    List<RankingDto> selectBookViewRanking();

    // 음악 조회 랭킹
    List<RankingDto> selectMusicViewRanking();

    // 추천 결과 좋아요 랭킹
    List<RankingDto> selectRecommendationLikeRanking();
    
    List<RankingDto> selectMbtiRanking(@Param("mbti") String mbti);
    
}