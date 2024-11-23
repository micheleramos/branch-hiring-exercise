package com.example.branch_hiring_exercise.controller;

import com.example.branch_hiring_exercise.dto.ExerciseResponseDto;
import com.example.branch_hiring_exercise.service.GithubUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GithubUserControllerTest {

    @Mock
    private GithubUserService githubUserService;

    @InjectMocks
    private GithubUserController githubUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetUserData() {
        // Mock the github api response
        String username = "octocat";
        ExerciseResponseDto mockResponse = new ExerciseResponseDto();
        mockResponse.setUserName("octocat");
        mockResponse.setDisplayName("The Octocat");
        mockResponse.setUrl("https://github.com/octocat");

        when(githubUserService.getUserData(username)).thenReturn(mockResponse);

        // Run test
        ExerciseResponseDto response = githubUserController.getUserData(username);

        // Asserts
        assertNotNull(response);
        assertEquals("octocat", response.getUserName());
        assertEquals("The Octocat", response.getDisplayName());
        assertEquals("https://github.com/octocat", response.getUrl());
    }
}
