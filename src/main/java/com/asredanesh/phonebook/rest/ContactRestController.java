package com.asredanesh.phonebook.rest;

import com.asredanesh.phonebook.dto.ContactAdvancedSearchDto;
import com.asredanesh.phonebook.dto.ContactDtoPassFromClient;
import com.asredanesh.phonebook.dto.GitHubUserDtoPassFromClient;
import com.asredanesh.phonebook.dto.ResultsOfContactAdvancedSearch;
import com.asredanesh.phonebook.mapper.ContactMapper;
import com.asredanesh.phonebook.model.Contact;
import com.asredanesh.phonebook.service.impl.ContactServiceImpl;
import com.asredanesh.phonebook.service.impl.GitHubUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
public class ContactRestController {
    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private GitHubUserServiceImpl gitHubUserService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ContactMapper contactMapper;

    @GetMapping(value = "/test")
    public ResponseEntity<Contact> getCreateContact() {
//        UriComponentsBuilder u
        String user1 = restTemplate.getForObject("https://api.github.com/users/r", String.class);
        GitHubUserDtoPassFromClient user = restTemplate.getForObject("https://api.github.com/users/r", GitHubUserDtoPassFromClient.class);
        System.out.println(user);
        System.out.println(user1);
        return null;
    }

    @PutMapping(value = "/create")
    public ResponseEntity<Contact> getCreateContact(@RequestBody ContactDtoPassFromClient passFromClient) {

        restTemplate.getForObject("https://api.github.com/users/" + passFromClient.getGitHubUser().getLogin(), GitHubUserDtoPassFromClient.class);

        return ResponseEntity.ok(contactService.createContact(passFromClient));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<ResultsOfContactAdvancedSearch>> getCreateContact(@RequestBody ContactAdvancedSearchDto contactAdvancedSearchDto) {
        List<Contact> contacts = contactService.doAdvancedSearch(contactAdvancedSearchDto);
        return ResponseEntity.ok(contactMapper.getContactListToDto(contacts));
    }
}
