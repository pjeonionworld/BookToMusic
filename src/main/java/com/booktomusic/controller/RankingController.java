package com.booktomusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.booktomusic.dto.RankingDto;
import com.booktomusic.service.RankingService;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/random-mbti-book")
    public RankingDto getRandomMbtiTopBook() {
        return rankingService.getRandomMbtiTopBook();
    }
    
    @PostMapping("/daily")
    public void saveDailyMbtiRanking() {
        rankingService.saveDailyMbtiRanking();
    }
    
    @GetMapping("/daily")
    public RankingDto getTodayMbtiRanking() {
        return rankingService.getTodayMbtiRanking();
    }
    
    
 // 책 조회 랭킹
    @GetMapping("/book")
    public List<RankingDto> getBookViewRanking() {
        return rankingService.getBookViewRanking();
    }

    // 음악 조회 랭킹
    @GetMapping("/music")
    public List<RankingDto> getMusicViewRanking() {
        return rankingService.getMusicViewRanking();
    }

    // 추천 결과 좋아요 랭킹
    @GetMapping("/recommendation")
    public List<RankingDto> getRecommendationLikeRanking() {
        return rankingService.getRecommendationLikeRanking();
    }
    
    
    @GetMapping("/mbti")
    public ResponseEntity<List<RankingDto>> getMbtiRanking(
            @RequestParam("mbti") String mbti) {

        List<RankingDto> rankingList = rankingService.getMbtiRanking(mbti);

        return ResponseEntity.ok(rankingList);
    }
    
}