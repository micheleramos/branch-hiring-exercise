package com.example.branch_hiring_exercise.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.example.branch_hiring_exercise.dto.ExerciseResponseDto;
import com.example.branch_hiring_exercise.dto.UserReposResponseDto;
import com.example.branch_hiring_exercise.dto.UserResponseDto;

@Service
public class GithubUserService {

	private final RestTemplate restTemplate;
	
	public GithubUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public String getUserData(String username) {

        // Get username's basic data
		UserResponseDto userData = restTemplate.getForObject(String.format("https://api.github.com/users/%s", username), 
        						UserResponseDto.class);
        
        System.out.println(userData.toString());
        
        // Get username's repositories
        List<UserReposResponseDto> repoData= restTemplate.exchange(
        	    String.format("https://api.github.com/users/%s/repos", username),
        	    HttpMethod.GET,
        	    null,
        	    new ParameterizedTypeReference<List<UserReposResponseDto>>() {}
        	).getBody();
        
        System.out.println(repoData.toString());
        
        // map to exercise response object
        ExerciseResponseDto response = new ExerciseResponseDto();
        
        response.setUserName(userData.getLogin());
        response.setDisplayName(userData.getName());
        response.setAvatar(userData.getAvatarUrl());
        response.setGeoLocation(userData.getLocation());
        response.setEmail(userData.getEmail());
        response.setUrl(userData.getHtmlUrl());
        response.setCreatedAt(userData.getCreatedAt());
        response.setRepos(repoData);
        

        return "You called the github apis!";
    }
	
	

}
