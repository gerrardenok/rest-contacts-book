package org.itechart.repository;

import org.itechart.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findByEmail(String email);

    public User findTopByEmailAndEnabled(String email, boolean enabled);

}