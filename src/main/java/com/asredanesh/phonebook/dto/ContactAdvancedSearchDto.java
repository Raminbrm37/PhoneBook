package com.asredanesh.phonebook.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactAdvancedSearchDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String gitHubAccount;
}
