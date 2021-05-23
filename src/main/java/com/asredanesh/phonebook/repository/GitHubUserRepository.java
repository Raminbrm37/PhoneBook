package com.asredanesh.phonebook.repository;

import com.asredanesh.phonebook.model.GitHubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubUserRepository extends JpaRepository<GitHubUser,Long> {
    GitHubUser findByLogin(String username);
}
