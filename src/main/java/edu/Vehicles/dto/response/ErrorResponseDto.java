package edu.Vehicles.dto.response;

import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDto {

	private int statuscode;
	private String error;
	private String message;
	private String path;
	private LocalDateTime timestamp;
	
	public static ErrorResponseDto failure(int statuscode,String error,String message,HttpServletRequest req) {
		return ErrorResponseDto.builder().statuscode(statuscode).error(error).message(message).path(req.getRequestURI()).build();
	}
}
