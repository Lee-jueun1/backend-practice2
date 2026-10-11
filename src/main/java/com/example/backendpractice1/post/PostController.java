
package com.example.backendpractice1.post;

import com.example.backendpractice1.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPost(
            @RequestBody(required = false) CreatePostRequest request) {

        if (request == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("요청 본문이 필요합니다."));
        }

        if (request.title() == null || request.title().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("title은 필수입니다."));
        }

        if (request.content() == null || request.content().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("content는 필수입니다."));
        }

        Post post = postService.createPost(
                request.title(),
                request.content()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(post));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> getPost(
            @PathVariable Long id
    ) {
        Post post = postService.getPost(id);

        return ResponseEntity.ok(
                ApiResponse.success(post)
        );
    }
    @ExceptionHandler(
            org.springframework.web.server.ResponseStatusException.class
    )
    public ResponseEntity<ApiResponse<Object>> handlePostError(
            org.springframework.web.server.ResponseStatusException ex
    ) {
        if (ex.getStatusCode().value() == 404) {
            return ResponseEntity.status(404)
                    .body(ApiResponse.error("게시글을 찾을 수 없습니다."));
        }

        return ResponseEntity.status(ex.getStatusCode())
                .body(ApiResponse.error("요청을 처리할 수 없습니다."));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updatePost(
            @PathVariable Long id,
            @RequestBody(required = false) CreatePostRequest request
    ) {
        if (request == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("요청 본문이 필요합니다."));
        }

        if (request.title() == null || request.title().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("title은 필수입니다."));
        }

        if (request.content() == null || request.content().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("content는 필수입니다."));
        }

        Post post = postService.updatePost(
                id,
                request.title(),
                request.content()
        );

        return ResponseEntity.ok(
                ApiResponse.success(post)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePost(
            @PathVariable Long id
    ) {
        postService.deletePost(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        java.util.Map.of(
                                "message", "게시글이 삭제되었습니다."
                        )
                )
        );
    }
}
