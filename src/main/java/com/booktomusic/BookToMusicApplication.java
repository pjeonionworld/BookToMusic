package com.booktomusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.booktomusic.mapper")
public class BookToMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookToMusicApplication.class, args);
    }
}