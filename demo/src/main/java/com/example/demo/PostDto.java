package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class PostDto {
    private Long id;
    private String title;       // 최대 30자 제한
    private String content;
    private String create_date;
    private List<CommentDto> comments = new ArrayList<>();

    public PostDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCreate_date() { return create_date; }
    public void setCreate_date(String create_date) { this.create_date = create_date; }
    public List<CommentDto> getComments() { return comments; }
    public void setComments(List<CommentDto> comments) { this.comments = comments; }
}