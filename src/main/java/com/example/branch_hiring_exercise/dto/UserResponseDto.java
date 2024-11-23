package com.example.branch_hiring_exercise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponseDto {
	
	// Dto will map fields from the GitHub API
    private String userName;
    private String displayName;
    private String avatar;
    private String geoLocation;
    private String email;
    private String url;            
    private String createdAt; 
    private List<UserReposResponseDto> repos;
}
