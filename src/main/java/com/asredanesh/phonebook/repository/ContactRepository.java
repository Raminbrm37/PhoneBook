package com.asredanesh.phonebook.repository;

import com.asredanesh.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
