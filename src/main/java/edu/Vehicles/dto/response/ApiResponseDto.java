package edu.Vehicles.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponseDto<T> {
	private int statusCode;
	private String message;
	private T data;
	private LocalDateTime timestamp;

	public static <T> ApiResponseDto<T> success(int statusCode, String message, T data) {
		return ApiResponseDto.<T>builder().statusCode(statusCode).message(message).data(data)
				.timestamp(LocalDateTime.now()).build();
	}

}
