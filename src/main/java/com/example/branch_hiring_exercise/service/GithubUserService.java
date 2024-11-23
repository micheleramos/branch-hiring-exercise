package com.example.branch_hiring_exercise.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.branch_hiring_exercise.dto.ExerciseResponseDto;
import com.example.branch_hiring_exercise.dto.UserReposResponseDto;
import com.example.branch_hiring_exercise.dto.UserResponseDto;
import com.example.branch_hiring_exercise.exception.UserNotFoundException;
import com.example.branch_hiring_exercise.exception.ExternalServiceException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GithubUserService {

    private final RestTemplate restTemplate;

    public GithubUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExerciseResponseDto getUserData(String username) {
        try {
            // retrieve  user's basic data
            UserResponseDto userData = retrieveUserData(username);

            // retrieve user's repositories
            List<UserReposResponseDto> repoData = retrieveUserRepos(username);

            // Map data to exercise response DTO
            return mapToExerciseResponse(userData, repoData);
        } catch (HttpClientErrorException.NotFound e) {
            log.error("User not found: {}", username, e);
            throw new UserNotFoundException("User '" + username + "' was not found.", e);
        } catch (RestClientException e) {
            log.error("Error communicating with GitHub API}", username, e);
            throw new ExternalServiceException("Failed to retrieve data.", e);
        } catch (Exception e) {
            log.error("Error occurred while fetching data for user: {}", username, e);
            throw new RuntimeException("Unexpected error occurred.", e);
        }
    }

    private UserResponseDto retrieveUserData(String username) {
        try {
            return restTemplate.getForObject(String.format("https://api.github.com/users/%s", username), 
					UserResponseDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw e; // will be caught
        } catch (RestClientException e) {
            log.error("Error fetching user data for: {}", username, e);
            throw new ExternalServiceException("Failed to fetch user data.", e);
        }
    }

    private List<UserReposResponseDto> retrieveUserRepos(String username) {
        try {
        	
        	return restTemplate.exchange(
            	    String.format("https://api.github.com/users/%s/repos", username),
            	    HttpMethod.GET,
            	    null, // get with no request entity
            	    new ParameterizedTypeReference<List<UserReposResponseDto>>() {}
            	).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw e; // will be caught
        } catch (RestClientException e) {
            log.error("Error fetching user repositories for: {}", username, e);
            throw new ExternalServiceException("Failed to fetch user repositories.", e);
        }
    }

    private ExerciseResponseDto mapToExerciseResponse(UserResponseDto userData, List<UserReposResponseDto> repoData) {
        ExerciseResponseDto response = new ExerciseResponseDto();
        response.setUserName(userData.getLogin());
        response.setDisplayName(userData.getName());
        response.setAvatar(userData.getAvatarUrl());
        response.setGeoLocation(userData.getLocation());
        response.setEmail(userData.getEmail());
        response.setUrl(userData.getHtmlUrl());
        response.setCreatedAt(userData.getCreatedAt());
        response.setRepos(repoData);
        return response;
    }
}