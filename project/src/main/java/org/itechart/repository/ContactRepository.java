package org.itechart.repository;

import org.itechart.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Contact findByIdAndUserId(Long id, Long userId);

    @Query("select c from Contact c " +
            "where c.user.id = :userId " +
            "and (c.name like :filter or c.tel like :filter)" +
            "order by c.modified")
    Page<Contact> search(@Param("userId") Long userId,
                         @Param("filter") String filter,
                         Pageable pageable);

}