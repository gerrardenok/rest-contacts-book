package org.itechart.repository;

import org.itechart.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContactRepository extends CrudRepository<Contact, Long> {

}