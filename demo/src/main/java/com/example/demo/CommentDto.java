package com.example.demo;

public class CommentDto {
    private Long id;
    private String content;     // 최대 200자 제한
    private String create_date;
    private Long post;          // 게시글 ID 저장용

    public CommentDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCreate_date() { return create_date; }
    public void setCreate_date(String create_date) { this.create_date = create_date; }
    public Long getPost() { return post; }
    public void setPost(Long post) { this.post = post; }
}