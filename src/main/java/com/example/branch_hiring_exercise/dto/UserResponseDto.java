package com.example.branch_hiring_exercise.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
public class UserResponseDto {
	
	// Dto will map fields from the GitHub API
    @JsonProperty("login")
    private String login;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("location")
    private String location;

    @JsonProperty("email")
    private String email;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("created_at")
    private String createdAt;
    
    // Lombok is not generating this method properly
    public String getHtmlUrl() {
    	return this.htmlUrl;
    }
}
