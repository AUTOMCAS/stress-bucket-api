package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.AuthException;
import com.stressbucket.stressbucketapi.model.User;

public interface UserService {
    User registerUser(String username, String password) throws AuthException;
}
