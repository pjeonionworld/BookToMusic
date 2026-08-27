package com.booktomusic.service;

import java.util.List;
import com.booktomusic.dto.RankingDto;

public interface RankingService {

    RankingDto getRandomMbtiTopBook();
    
    void saveDailyMbtiRanking();

    RankingDto getTodayMbtiRanking();
    
    List<RankingDto> getBookViewRanking();

    List<RankingDto> getMusicViewRanking();

    List<RankingDto> getRecommendationLikeRanking();
    
    List<RankingDto> getMbtiRanking(String mbti);
}