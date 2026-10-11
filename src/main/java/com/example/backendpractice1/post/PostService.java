
package com.example.backendpractice1.post;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PostService {

    private final AtomicLong sequence = new AtomicLong(0);

    private final ConcurrentMap<Long, Post> posts =
            new ConcurrentHashMap<>();

    public Post createPost(String title, String content) {
        Long id = sequence.incrementAndGet();

        Post post = new Post(id, title, content);
        posts.put(id, post);

        return post;
    }

    public Post getPost(Long id) {
        Post post = posts.get(id);

        if (post == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "게시글을 찾을 수 없습니다."
            );
        }

        return post;
    }
    public Post updatePost(Long id, String title, String content) {
        Post existingPost = posts.get(id);

        if (existingPost == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "게시글을 찾을 수 없습니다."
            );
        }

        Post updatedPost = new Post(id, title, content);
        posts.put(id, updatedPost);

        return updatedPost;
    }
    public void deletePost(Long id) {
        Post removedPost = posts.remove(id);

        if (removedPost == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "게시글을 찾을 수 없습니다."
            );
        }
    }
}

