package com.nisum.msusuario.repository;

import com.nisum.msusuario.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findByName(String name);

    public Optional<User> findByEmail(String email);
}
