package com.asredanesh.phonebook.service;

import com.asredanesh.phonebook.dto.ContactAdvancedSearchDto;
import com.asredanesh.phonebook.dto.ContactDtoPassFromClient;
import com.asredanesh.phonebook.model.Contact;

import java.util.List;


public interface ContactService  extends BaseEntityService<Contact,Long>{
    Contact createContact(ContactDtoPassFromClient passFromClient);
    List<Contact> doAdvancedSearch(ContactAdvancedSearchDto contactAdvancedSearchDto);
}
