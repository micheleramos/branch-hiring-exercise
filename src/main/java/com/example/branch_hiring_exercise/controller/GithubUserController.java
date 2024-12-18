package com.example.branch_hiring_exercise.controller;

import com.example.branch_hiring_exercise.dto.ExerciseResponseDto;
import com.example.branch_hiring_exercise.service.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class GithubUserController {

    private final GithubUserService githubUserService;

    @GetMapping("/{username}")
    public ExerciseResponseDto getUserData(@PathVariable String username) {
        return githubUserService.getUserData(username);
    }
}
