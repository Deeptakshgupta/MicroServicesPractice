package com.userService.payLoad;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// For handling Exception and Providing Variables that may provide info About Error
// this can also be used for logs

// Same thing have been done in Other Service -> HotelService Using Map key pair Value.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

	private String message;
	private boolean success;
	private HttpStatus status;
}
