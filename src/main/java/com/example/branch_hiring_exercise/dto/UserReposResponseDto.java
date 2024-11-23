package com.example.branch_hiring_exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReposResponseDto {
	
	// Dto will map fields from the GitHub API
    private String name;
    private String url;
}