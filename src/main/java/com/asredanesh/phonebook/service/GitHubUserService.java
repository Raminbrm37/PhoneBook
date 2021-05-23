package com.asredanesh.phonebook.service;

import com.asredanesh.phonebook.model.GitHubUser;

import java.util.LinkedHashMap;
import java.util.List;

public interface GitHubUserService extends BaseEntityService<GitHubUser,Long> {
    Boolean checkExistGitHubUsername(String username);
    GitHubUser createGitHubUser(String username);
    List<LinkedHashMap<String, String>> test(String gitHubUsername);
}
