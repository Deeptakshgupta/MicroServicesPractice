package com.getway.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getway.model.AuthResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

/**We would be using objects provided by Okta to populate data for AuthResponse **/	
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			)
	{
		log.info("user Email id:", user.getEmail());
		
		//creating Auth Response objejct
		AuthResponse authResponse = new AuthResponse();
		
		//setting email to authResponse
		authResponse.setUserId(user.getEmail());
		
		//AccessToken set-> get the access token then taking out it's value
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		
		//refresh Token set-> get refresh Token then take out the value from the token we got
		authResponse.setRereshToken(client.getRefreshToken().getTokenValue());
		
		//expire time set
		authResponse.setExpiresAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		//For Authorities, we have to pass Collection hence first have to create the Collection class

		//		user.getAuthorities()  -> Get the Collection of GrantedAuthoritys associated with this OAuth2.0 token.   
		///Returns: Collection of grantedAuthorities but we require String hence we convert of first to Collection of Strings
		List<String> authAuthoritites = user.getAuthorities().stream().map(grantedAuthority -> {
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		
		authResponse.setAuthorities(authAuthoritites);
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
//		return new ResponseEntity.ok(authResponse);
	}
}
