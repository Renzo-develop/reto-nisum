package com.nisum.msusuario.service;

import com.nisum.msusuario.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUsers();

    public User createUser(User user);

    public User updateUser(User user);

    public Optional<User> findByEmail(String email);

    public void delete(String id);

}
