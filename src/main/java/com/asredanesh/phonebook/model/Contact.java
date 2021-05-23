package com.asredanesh.phonebook.model;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends BaseEntity<Long> {

    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "githubuser_id")
    private GitHubUser gitHubUser;

}
