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
        String response1 = restTemplate.getForObject(String.format("https://api.github.com/users/%s", username), 
        						Map.class).toString();
        
        System.out.println(response1);
        
        // Get username's repositories
        String response2 = restTemplate.getForObject(String.format("https://api.github.com/users/%s/repos", username), 
				List.class).toString();
        
        System.out.println(response2);


        return "You called the github apis!";
    }
	
	

}
