package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.AuthException;
import com.stressbucket.stressbucketapi.model.User;

public interface UserRepository {
    Integer create(String username, String password) throws AuthException;

    User findByUsernameAndPassword(String username, String password) throws AuthException;

    Integer getCountByUsername(String username);

    User findById(Integer userId);
}
