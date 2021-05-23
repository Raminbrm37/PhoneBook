package com.asredanesh.phonebook.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultsOfContactAdvancedSearch {
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String gitHubAccount;
    private Integer repositorySize;
}
