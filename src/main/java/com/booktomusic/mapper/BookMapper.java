package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booktomusic.dto.BookDto;

@Mapper
public interface BookMapper {

    int insertBook(BookDto bookDto);

    BookDto findBookByIsbn(
            @Param("isbn") String isbn
    );
}