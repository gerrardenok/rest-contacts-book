package org.itechart.repository;

import org.itechart.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}