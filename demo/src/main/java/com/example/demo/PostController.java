package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private List<PostDto> postList = new ArrayList<>();
    private Long nextPostId = 1L;
    private Long nextCommentId = 1L;

    // 현재 날짜를 "yyyy-MM-dd" 형식의 문자열로 반환하는 공통 메서드
    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // 1. 게시글 전체 조회
    @GetMapping("/posts")
    public List<PostDto> getPosts() {
        return postList;
    }

    // 2. 게시글 작성 (날짜 자동 등록)
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDto requestDto) {
        if (requestDto.getTitle().length() > 30) {
            return ResponseEntity.badRequest().body("제목은 30자를 초과할 수 없습니다.");
        }
        requestDto.setId(nextPostId++);
        requestDto.setCreate_date(getCurrentDate());
        postList.add(requestDto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "성공적으로 등록됐습니다.");
        return ResponseEntity.ok(response);
    }

    // 3. 게시글 개별 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        for (PostDto post : postList) {
            if (post.getId().equals(postId)) return ResponseEntity.ok(post);
        }
        return getErrorResponse();
    }

    // 4. 댓글 조회
    @GetMapping("/posts/{postId}/comment")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        for (PostDto post : postList) {
            if (post.getId().equals(postId)) {
                Map<String, Object> response = new HashMap<>();
                response.put("comments", post.getComments());
                return ResponseEntity.ok(response);
            }
        }
        return getErrorResponse();
    }

    // 5. 댓글 작성 (날짜 자동 등록)
    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        if (commentDto.getContent().length() > 200) {
            return ResponseEntity.badRequest().body("댓글은 200자를 초과할 수 없습니다.");
        }

        for (PostDto post : postList) {
            if (post.getId().equals(postId)) {
                commentDto.setId(nextCommentId++);
                commentDto.setCreate_date(getCurrentDate()); // <--- 호출 시점 날짜 자동 삽입
                commentDto.setPost(postId);
                post.getComments().add(commentDto);

                Map<String, String> response = new HashMap<>();
                response.put("message", "성공적으로 등록됐습니다.");
                return ResponseEntity.ok(response);
            }
        }
        return getErrorResponse();
    }

    private ResponseEntity<?> getErrorResponse() {
        Map<String, Object> error = new HashMap<>();
        error.put("status_code", 404);
        error.put("error", "POST_NOT_FOUND");
        error.put("message", "존재하지 않는 게시글입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}