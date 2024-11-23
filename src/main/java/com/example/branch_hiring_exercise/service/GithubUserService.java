package com.example.branch_hiring_exercise.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class GithubUserService {

	private final RestTemplate restTemplate;
	
	public GithubUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public String getUserData(String username) {
		return "Hello, World!";
	}
	
	public String getUserData2(String username) {

        // Get username's basic data
        restTemplate.getForObject(String.format("https://api.github.com/users/%s", username), 
        						Map.class);
        
        // Get username's repositories
        restTemplate.getForObject(String.format("https://api.github.com/users/%s/repos", username), 
				List.class);


        return "You called the github apis!";
    }
	
	

}
