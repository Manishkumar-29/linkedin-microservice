package com.linkedProject.posts_service.controller;

import com.linkedProject.posts_service.dto.PostCreateRequestDTO;
import com.linkedProject.posts_service.dto.PostDTO;
import com.linkedProject.posts_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDto,
                                              HttpServletRequest httpServletRequest) {
        PostDTO postDto = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
        PostDTO postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDTO>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostDTO> posts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
