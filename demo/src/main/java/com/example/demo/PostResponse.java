package com.example.demo;

import java.util.List;

public class PostResponse {
    private List<PostDto> posts;


    public PostResponse(List<PostDto> posts) {
        this.posts = posts;
    }


    public List<PostDto> getPosts() {
        return posts;
    }

    public static class PostDto {
        private Long id;
        private String title;
        private String content;
        private String create_date;

        public PostDto(Long id, String title, String content, String create_date) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.create_date = create_date;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String getCreate_date() { return create_date; }
    }
}