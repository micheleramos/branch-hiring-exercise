package com.example.branch_hiring_exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class GithubUserServiceTest {

	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubUserService githubUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
