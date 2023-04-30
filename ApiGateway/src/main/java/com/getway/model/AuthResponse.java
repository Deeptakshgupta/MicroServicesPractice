package com.getway.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
We will be retrieving data for AuthResponse Data from the objects provided by Okta
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String userId;
	private String accessToken;
	private String rereshToken;
	private long expiresAt;
	private Collection<String> authorities;
}
