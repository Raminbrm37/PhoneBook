package com.asredanesh.phonebook.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUser extends BaseEntity<Long>{
    private String login;

    @ElementCollection
    private Set<GitHubRepository> gitHubRepositorySet;

    private Integer repositorySize;


    @PrePersist
    public void getDetectionSize(){
        repositorySize=gitHubRepositorySet.size();
    }
}
