package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class BucketRepositoryImpl implements BucketRepository{

    private static final String SQL_CREATE = "INSERT INTO BUCKETS(ID, NAME, STRESS_LEVEL) VALUES(NEXTVAL('BUCKETS_SEQ'), ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT ID, NAME, STRESS_LEVEL " + "FROM BUCKETS WHERE ID = ?";
    private static final String SQL_DELETE_BUCKET = "DELETE FROM BUCKETS WHERE ID = ?";
    private static final String SQL_UPDATE = "UPDATE BUCKETS SET NAME = ?, STRESS_LEVEL = ? " + "WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String bucketName, Integer stressLevel) throws BadReqestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, bucketName);
                ps.setInt(2, stressLevel);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        } catch (Exception e){
            throw new BadReqestException("Failed to create bucket. Invalid details.");
        }
    }

    @Override
    public Bucket findById(Integer bucketId) throws BadReqestException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{bucketId}, bucketRowMapper);
    }

//    Convert row into an object
    private RowMapper<Bucket> bucketRowMapper = ((rs, rowNum) -> {
        return new Bucket(rs.getInt("ID"),
                rs.getString("NAME"),
                rs.getInt("STRESS_LEVEL"));
    });

    @Override
    public void removeById(Integer bucketId) {
        jdbcTemplate.update(SQL_DELETE_BUCKET, new Object[]{bucketId});
    }

    @Override
    public void update(Integer bucketId, Bucket bucket) throws BadReqestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{bucket.getBucketName(), bucket.getStressLevel(), bucketId});
        }catch (Exception e) {
            throw new BadReqestException("Invalid request");
        }
    }
}
