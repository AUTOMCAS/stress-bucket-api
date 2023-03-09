package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.AuthException;
import com.stressbucket.stressbucketapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static final String SQL_CREATE = "INSERT INTO USERS(ID, USERNAME, PASSWORD) VALUES(NEXTVAL('USERS_SEQ'), ?, ?)";
    private static final String SQL_COUNT_BY_USERNAME = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?";
    private static final String SQL_FIND_BY_ID = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";
    private static final String SQL_FIND_BY_USERNAME = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String username, String password) throws AuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        }catch (Exception e) {
            throw new AuthException("Invalid details. Failed to create user" + e);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws AuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new Object[]{username}, userRowMapper);
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new AuthException("Invalid username/password");
            return user;
        }catch (Exception e) {
            throw new AuthException("Invalid username/password");
        }
    }

    @Override
    public Integer getCountByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_USERNAME, new Object[]{username}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {

        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("ID"),
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"));
    });
}
