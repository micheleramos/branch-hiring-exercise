package com.example.branch_hiring_exercise.dto;

import java.util.List;
import java.util.ArrayList;

import com.example.branch_hiring_exercise.dto.UserReposResponseDto;

import org.springframework.format.datetime.standard.DateTimeContext;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExerciseResponseDto {

	private String userName;      // Maps to "login"
    private String displayName;   // Maps to "name"
    private String avatar;        // Maps to "avatar_url"
    private String geoLocation;   // Maps to "location"
    private String email;         // Maps to "email"
    private String url;           // Maps to "html_url"
    private String createdAt; // Maps to "created_at"
    private List<UserReposResponseDto> repos; // List of repositories
	
    
    public void setRepos(List<UserReposResponseDto> repoData) {
    	if (repoData != null) { // null check first
            this.repos = repoData.stream()
                .map(repo -> new UserReposResponseDto(repo.getName(), repo.getUrl()))
                .toList();
        } else { // if null repo list, instantiate empty list
            this.repos = new ArrayList<>();
        }
	}

}
