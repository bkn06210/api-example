package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @GetMapping("/posts")
    public PostResponse getPosts() {

        List<PostResponse.PostDto> postList = new ArrayList<>();


        postList.add(new PostResponse.PostDto(1L, "안녕하세요", "반가워요", "2025-06-30"));
        postList.add(new PostResponse.PostDto(2L, "안녕하세요", "반가워요", "2025-06-30"));


        return new PostResponse(postList);
    }
}