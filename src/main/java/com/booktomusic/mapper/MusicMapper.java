package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booktomusic.dto.MusicDto;

@Mapper
public interface MusicMapper {

    MusicDto selectMusicByIdx(@Param("musicIdx") int musicIdx);
    
    MusicDto selectMusicByTitleAndArtist(@Param("musicTitle") String musicTitle,@Param("musicArtist") String musicArtist);
    
    int insertMusic(MusicDto musicDto);
}