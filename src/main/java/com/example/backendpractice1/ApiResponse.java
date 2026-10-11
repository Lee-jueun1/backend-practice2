
package com.example.backendpractice1;

public record ApiResponse<T>(
        String status,
        T data
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data);
    }

    public static ApiResponse<Object> error(String message) {
        return new ApiResponse<>(
                "error",
                java.util.Map.of("message", message)
        );
    }
}
