package org.itechart.repository;

import org.itechart.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Contact findByIdAndUserId(Long id, Long userId);

    Page<Contact> findByUserIdAndNameLikeOrTelLikeOrderByModifiedDesc(Long userId, String name, String tel, Pageable pageable);

}