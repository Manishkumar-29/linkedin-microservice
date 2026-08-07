package com.linkedProject.posts_service.service;

import com.linkedProject.posts_service.dto.PostCreateRequestDTO;
import com.linkedProject.posts_service.dto.PostDTO;
import com.linkedProject.posts_service.entity.PostEntity;
import com.linkedProject.posts_service.exception.ResourceNotFoundException;
import com.linkedProject.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDTO createPost(PostCreateRequestDTO postCreateRequestDto, Long userId) {
        log.info("Creating post for user with id: {}", userId);
        PostEntity post = modelMapper.map(postCreateRequestDto, PostEntity.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO getPostById(Long postId) {
        log.info("Getting the post with ID: {}", postId);
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found " +
                "with ID: "+postId));
        return modelMapper.map(post, PostDTO.class);
    }

    public List<PostDTO> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with ID: {}", userId);
        List<PostEntity> postList = postRepository.findByUserId(userId);

        return postList
                .stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .collect(Collectors.toList());
    }
}
