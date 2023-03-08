package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.AuthException;
import com.stressbucket.stressbucketapi.model.User;

public interface UserService {
    User register(String username, String password) throws AuthException;

    User validate(String username, String password) throws AuthException;
}
