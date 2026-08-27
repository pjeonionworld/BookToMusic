package com.booktomusic.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class BookDto {

    private Integer bookIdx;
    private String isbn;
    private String bookTitle;
    private String bookAuthor;
    private String publisher;
    private String bookThumbnail;
    private String bookContents;
    private LocalDate publishedDate;
    private Timestamp createdAt;

    public BookDto() {
    }

    public BookDto(
            Integer bookIdx,
            String isbn,
            String bookTitle,
            String bookAuthor,
            String publisher,
            String bookThumbnail,
            String bookContents,
            LocalDate publishedDate,
            Timestamp createdAt) {

        this.bookIdx = bookIdx;
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.bookThumbnail = bookThumbnail;
        this.bookContents = bookContents;
        this.publishedDate = publishedDate;
        this.createdAt = createdAt;
    }

    public Integer getBookIdx() {
        return bookIdx;
    }

    public void setBookIdx(Integer bookIdx) {
        this.bookIdx = bookIdx;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookThumbnail() {
        return bookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        this.bookThumbnail = bookThumbnail;
    }

    public String getBookContents() {
        return bookContents;
    }

    public void setBookContents(String bookContents) {
        this.bookContents = bookContents;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}