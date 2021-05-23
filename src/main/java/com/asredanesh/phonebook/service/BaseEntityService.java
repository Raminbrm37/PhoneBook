package com.asredanesh.phonebook.service;

import com.asredanesh.phonebook.model.BaseEntity;
import com.asredanesh.phonebook.util.exception.GitHubAccountNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityService <T extends BaseEntity<E>,E extends Serializable> {
    T save(T entity) throws Exception;
    T findById(E id);
    List<T> findAll();
    Boolean remove(E entity);
    Boolean removeById(T id);



}
