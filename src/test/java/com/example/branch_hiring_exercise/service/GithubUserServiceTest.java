package com.example.branch_hiring_exercise.service;

import com.example.branch_hiring_exercise.dto.ExerciseResponseDto;
import com.example.branch_hiring_exercise.dto.UserReposResponseDto;
import com.example.branch_hiring_exercise.dto.UserResponseDto;
import com.example.branch_hiring_exercise.exception.ExternalServiceException;
import com.example.branch_hiring_exercise.exception.UserNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GithubUserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubUserService githubUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserDataHappyPath() {
        // Mock user data retrieval
        UserResponseDto mockUserResponse = new UserResponseDto();
        mockUserResponse.setLogin("octocat");
        mockUserResponse.setName("The Octocat");
        mockUserResponse.setAvatarUrl("https://avatars3.githubusercontent.com/u/583231?v=4");
        mockUserResponse.setLocation("San Francisco");
        mockUserResponse.setEmail(null);
        mockUserResponse.setHtmlUrl("https://github.com/octocat");
        mockUserResponse.setCreatedAt("2011-01-25T18:44:36Z");

        when(restTemplate.getForObject(
            eq("https://api.github.com/users/octocat"),
            eq(UserResponseDto.class)
        )).thenReturn(mockUserResponse);

        // Mocking repo data retrieval
        UserReposResponseDto mockRepo1 = new UserReposResponseDto("repo1", "https://github.com/octocat/repo1");
        UserReposResponseDto mockRepo2 = new UserReposResponseDto("repo2", "https://github.com/octocat/repo2");

        List<UserReposResponseDto> mockRepos = Arrays.asList(mockRepo1, mockRepo2);

        when(restTemplate.exchange(
            eq("https://api.github.com/users/octocat/repos"),
            eq(HttpMethod.GET),
            eq(null),
            any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(mockRepos));

        // run
        ExerciseResponseDto response = githubUserService.getUserData("octocat");

        // asserts
        assertNotNull(response);
        assertEquals("octocat", response.getUserName());
        assertEquals("The Octocat", response.getDisplayName());
        assertEquals("https://avatars3.githubusercontent.com/u/583231?v=4", response.getAvatar());
        assertEquals("San Francisco", response.getGeoLocation());
        assertEquals(null, response.getEmail());
        assertEquals("https://github.com/octocat", response.getUrl());
        assertEquals("2011-01-25T18:44:36Z", response.getCreatedAt());
        assertEquals(2, response.getRepos().size());
    }

    @Test
    public void testGetUserDataUserNotFoundError() {
        // mock exception
        when(restTemplate.getForObject(
            eq("https://api.github.com/users/jdfal320"),
            eq(UserResponseDto.class)
        )).thenThrow(HttpClientErrorException.NotFound.class);

        // Expecting UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> githubUserService.getUserData("jdfal320"));
    }

    @Test
    public void testGetUserDataReposNotFound() {
        // Mocking user data response
        UserResponseDto mockUserResponse = new UserResponseDto();
        mockUserResponse.setLogin("octocat");
        mockUserResponse.setName("The Octocat");

        when(restTemplate.getForObject(
            eq("https://api.github.com/users/octocat"),
            eq(UserResponseDto.class)
        )).thenReturn(mockUserResponse);

        // mocking repository not found exception
        when(restTemplate.exchange(
            eq("https://api.github.com/users/octocat/repos"),
            eq(HttpMethod.GET),
            eq(null),
            any(ParameterizedTypeReference.class)
        )).thenThrow(HttpClientErrorException.NotFound.class);

        // expecting UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> githubUserService.getUserData("octocat"));
    }

    @Test
    public void testGetUserData_UnexpectedError() {
        // mock user data retrieval
        when(restTemplate.getForObject(
            eq("https://api.github.com/users/octocat"),
            eq(UserResponseDto.class)
        )).thenThrow(new RuntimeException("Unexpected error"));

        // expecting RuntimeException
        assertThrows(RuntimeException.class, () -> githubUserService.getUserData("octocat"));
    }

    @Test
    public void testGetUserData_EmptyRepos() {
        // mock user data retireval
        UserResponseDto mockUserResponse = new UserResponseDto();
        mockUserResponse.setLogin("octocat");
        mockUserResponse.setName("The Octocat");

        when(restTemplate.getForObject(
            eq("https://api.github.com/users/octocat"),
            eq(UserResponseDto.class)
        )).thenReturn(mockUserResponse);

        // mock empty repos retrieval
        when(restTemplate.exchange(
            eq("https://api.github.com/users/octocat/repos"),
            eq(HttpMethod.GET),
            eq(null),
            any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        // run
        ExerciseResponseDto response = githubUserService.getUserData("octocat");

        // asserts
        assertNotNull(response);
        assertEquals("octocat", response.getUserName());
        assertEquals(0, response.getRepos().size());
    }
}
