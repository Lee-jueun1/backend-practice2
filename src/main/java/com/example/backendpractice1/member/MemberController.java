
package com.example.backendpractice1.member;

import com.example.backendpractice1.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Member>> createMember(
            @Valid @RequestBody CreateMemberRequest request) {

        Member member = memberService.createMember(
                request.name(),
                request.email()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(member));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationError(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("요청 값이 올바르지 않습니다.");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<java.util.List<Member>>> getAllMembers() {
        return ResponseEntity.ok(
                ApiResponse.success(memberService.getAllMembers())
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody(required = false) CreateMemberRequest request
    ) {
        if (request == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("요청 본문이 필요합니다."));
        }

        Member member = memberService.updateMember(
                id,
                request.name(),
                request.email()
        );

        if (member == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("회원을 찾을 수 없습니다."));
        }

        return ResponseEntity.ok(
                ApiResponse.success(member)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        java.util.Map.of(
                                "message", "회원이 삭제되었습니다."
                        )
                )
        );
    }

}
