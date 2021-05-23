package com.asredanesh.phonebook.service.impl;

import com.asredanesh.phonebook.dto.ContactAdvancedSearchDto;
import com.asredanesh.phonebook.dto.ContactDtoPassFromClient;
import com.asredanesh.phonebook.model.Contact;
import com.asredanesh.phonebook.model.GitHubUser;
import com.asredanesh.phonebook.repository.ContactRepository;
import com.asredanesh.phonebook.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GitHubUserServiceImpl gitHubUserService;

    @Override
    public Contact save(Contact entity) {

        return contactRepository.save(entity);
    }

    @Override
    public Contact findById(Long id) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Boolean remove(Long entity) {
        return null;
    }

    @Override
    public Boolean removeById(Contact id) {
        return null;
    }

    @Override
    public Contact createContact(ContactDtoPassFromClient passFromClient) {
        GitHubUser git = gitHubUserService.createGitHubUser(passFromClient.getGitHubUser().getLogin());
        Contact contact = modelMapper.map(passFromClient, Contact.class);
//        contact.setGitHubUser(git);
//        contactRepository.saveAndFlush(contact);
        contact.setGitHubUser(git);
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> doAdvancedSearch(ContactAdvancedSearchDto contactAdvancedSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> query = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> from = query.from(Contact.class);
        List<Predicate> predicates = getPredicates(contactAdvancedSearchDto, criteriaBuilder, from);
        query.where(
                criteriaBuilder.and(
                        predicates.toArray(new Predicate[0]))
        );
        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> getPredicates(ContactAdvancedSearchDto contact, CriteriaBuilder criteriaBuilder, Root<Contact> from) {
        List<Predicate> predicates = new ArrayList<>();
        setName(contact.getName(), criteriaBuilder, from, predicates);
        setPhoneNumber(contact.getPhoneNumber(), criteriaBuilder, from, predicates);
        setEmail(contact.getEmail(), criteriaBuilder, from, predicates);
        setOrganization(contact.getOrganization(), criteriaBuilder, from, predicates);
        setGitHubAccount(contact.getGitHubAccount(), criteriaBuilder, from, predicates);
        return predicates;
    }

    private void setGitHubAccount(String gitHubAccount, CriteriaBuilder criteriaBuilder, Root<Contact> from, List<Predicate> predicates) {
        if (gitHubAccount != null && !gitHubAccount.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            from.get("gitHubUser").get("login"), "%" + gitHubAccount.trim() + "%"
                    )
            );
        }
    }

    private void setOrganization(String organization, CriteriaBuilder criteriaBuilder, Root<Contact> from, List<Predicate> predicates) {
        if (organization != null && !organization.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            from.get("organization"), "%" + organization.trim() + "%"
                    )
            );
        }
    }

    private void setEmail(String email, CriteriaBuilder criteriaBuilder, Root<Contact> from, List<Predicate> predicates) {
        if (email != null && !email.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            from.get("email"), "%" + email.trim() + "%"
                    )
            );
        }
    }

    private void setPhoneNumber(String phoneNumber, CriteriaBuilder criteriaBuilder, Root<Contact> from, List<Predicate> predicates) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            from.get("phoneNumber"), "%" + phoneNumber.trim() + "%"
                    )
            );
        }
    }

    private void setName(String name, CriteriaBuilder criteriaBuilder, Root<Contact> from, List<Predicate> predicates) {
        if (name != null && !name.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(from.get("name"), "%" + name.trim() + "%")
            );
        }
    }
}
