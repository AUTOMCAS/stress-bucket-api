package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.AuthException;
import com.stressbucket.stressbucketapi.model.User;
import com.stressbucket.stressbucketapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String username, String password) throws AuthException {
        Integer count = userRepository.getCountByUsername(username);
        if(count > 0)
            throw new AuthException("Username already in use");
        Integer userId = userRepository.create(username, password);
        return userRepository.findById(userId);
    }

    @Override
    public User validate(String username, String password) throws AuthException {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
