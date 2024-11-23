package com.example.branch_hiring_exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserReposResponseDto {
	
	// Dto will map fields from the GitHub API
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("html_url")
    private String url;
	
	public UserReposResponseDto(String name, String url) {
        this.name = name;
        this.url = url;
    }
}