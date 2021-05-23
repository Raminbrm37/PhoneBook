package com.asredanesh.phonebook.mapper;

import com.asredanesh.phonebook.dto.ResultsOfContactAdvancedSearch;
import com.asredanesh.phonebook.model.Contact;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Service
public class ContactMapper {

    public ResultsOfContactAdvancedSearch getContactToDto(Contact contact) {
        ResultsOfContactAdvancedSearch results = new ResultsOfContactAdvancedSearch();
        results.setName(contact.getName());
        results.setEmail(contact.getEmail());
        results.setPhoneNumber(contact.getPhoneNumber());
        results.setOrganization(contact.getOrganization());
        results.setGitHubAccount(contact.getGitHubUser().getLogin());
        results.setRepositorySize(contact.getGitHubUser().getRepositorySize());
        return results;
    }

    public List<ResultsOfContactAdvancedSearch> getContactListToDto(List<Contact> contactList) {
        List<ResultsOfContactAdvancedSearch> results = new ArrayList<>();
        contactList.forEach(contact -> results.add(getContactToDto(contact)));
        return results;
    }
}
