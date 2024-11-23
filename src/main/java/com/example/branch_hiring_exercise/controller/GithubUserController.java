package com.example.branch_hiring_exercise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.branch_hiring_exercise.service.GithubUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class GithubUserController {
	
	private final GithubUserService githubUserService = new GithubUserService();

	@GetMapping("/{username}")
    public String getUserData(@PathVariable String username) {
        return githubUserService.getUserData(username);
    }
}


