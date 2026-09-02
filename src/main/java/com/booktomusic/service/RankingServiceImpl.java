package com.booktomusic.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booktomusic.dto.RankingDto;
import com.booktomusic.mapper.RankingMapper;
import com.booktomusic.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

    private static final List<String> MBTI_LIST = List.of(
            "ISTJ","ISFJ","INFJ","INTJ","ISTP","ISFP","INFP","INTP",
            "ESTP","ESFP","ENFP","ENTP","ESTJ","ESFJ","ENFJ","ENTJ"
    );

    private final RankingMapper rankingMapper;

    public RankingServiceImpl(RankingMapper rankingMapper) {
        this.rankingMapper = rankingMapper;
    }
    
    @Override
    public RankingDto getRandomMbtiTopBook() {
    	
    	//데일리 랭킹 랜덤으로 mbti 뽑기
        List<String> shuffledMbtiList =new ArrayList<>(MBTI_LIST);
        Collections.shuffle(shuffledMbtiList);

        for (String mbti : shuffledMbtiList) {
            RankingDto rankingDto =rankingMapper.selectTopBookByMbti(mbti);

            if (rankingDto != null) {
                return rankingDto;
            }
        }

        throw new IllegalStateException("MBTI별 책 추천 기록이 존재하지 않습니다.");
    }
    
    //데일리 랭킹 배치 로직 o_O!!
    @Override
    @Transactional
    @Scheduled(cron = "0 30 4 * * *",zone = "Asia/Seoul")
    public void saveDailyMbtiRanking() {

        RankingDto todayRankingDto =rankingMapper.selectTodayMbtiRanking();

        if (todayRankingDto != null) {
            return;
        }

        RankingDto rankingDto =getRandomMbtiTopBook();
        rankingDto.setRankingDate(LocalDate.now(ZoneId.of("Asia/Seoul")));
        int insertResult =rankingMapper.insertDailyMbtiRanking(rankingDto);

        if (insertResult != 1) {
            throw new IllegalStateException("오늘의 MBTI 책 랭킹 저장에 실패했습니다.");
        }
    }

    @Override
    public RankingDto getTodayMbtiRanking() {

        RankingDto rankingDto =rankingMapper.selectTodayMbtiRanking();

        if (rankingDto == null) {
            throw new IllegalStateException("오늘의 MBTI 책 랭킹이 존재하지 않습니다.");
        }

        return rankingDto;
    }
    
    @Override
    public List<RankingDto> getBookViewRanking() {
        return rankingMapper.selectBookViewRanking();
    }

    @Override
    public List<RankingDto> getMusicViewRanking() {
        return rankingMapper.selectMusicViewRanking();
    }

    @Override
    public List<RankingDto> getRecommendationLikeRanking() {
        return rankingMapper.selectRecommendationLikeRanking();
    }
    
    @Override
    public List<RankingDto> getMbtiRanking(String mbti) {
        return rankingMapper.selectMbtiRanking(mbti);
    }
}