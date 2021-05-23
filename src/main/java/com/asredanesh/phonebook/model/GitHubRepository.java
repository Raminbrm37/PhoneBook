package com.asredanesh.phonebook.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepository {
    private String name;
}
