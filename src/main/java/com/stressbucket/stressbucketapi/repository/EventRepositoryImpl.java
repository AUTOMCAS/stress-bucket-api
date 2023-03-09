package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String SQL_CREATE = "INSERT INTO EVENTS(ID, USER_ID, BUCKET_ID, STRESS_TYPE, DESCRIPTION, DATE_TIME, STRESS_LEVEL_CHANGE, RESULTING_STRESS_LEVEL) VALUES(NEXTVAL('EVENTS_SEQ'), ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT ID, USER_ID, BUCKET_ID, STRESS_TYPE, DESCRIPTION, DATE_TIME, STRESS_LEVEL_CHANGE, RESULTING_STRESS_LEVEL FROM EVENTS WHERE USER_ID = ? AND BUCKET_ID = ? AND ID = ?";
    private static final String SQL_FIND_ALL = "SELECT ID, USER_ID, BUCKET_ID, STRESS_TYPE, DESCRIPTION, DATE_TIME, STRESS_LEVEL_CHANGE, RESULTING_STRESS_LEVEL FROM EVENTS WHERE USER_ID = ? AND BUCKET_ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Event> eventRowMapper = ((rs, rowNum) -> {
        return new Event(rs.getInt("ID"),
                rs.getInt("USER_ID"),
                rs.getInt("BUCKET_ID"),
                rs.getString("STRESS_TYPE"),
                rs.getString("DESCRIPTION"),
                rs.getObject("DATE_TIME", LocalDateTime.class),
                rs.getInt("STRESS_LEVEL_CHANGE"),
                rs.getInt("RESULTING_STRESS_LEVEL"));
    });

    @Override
    public Integer create(Integer userId, Integer bucketId, String stressType, String description, LocalDateTime dateTime, Integer stressLevelChange, Integer resultingStressLevel) throws BadReqestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, bucketId);
                ps.setString(3, stressType);
                ps.setString(4, description);
                ps.setObject(5, dateTime);
                ps.setInt(6, stressLevelChange);
                ps.setInt(7, resultingStressLevel);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        } catch (Exception e){
            throw new BadReqestException("Failed to create event. Invalid details." + e);
        }
    }

    @Override
    public Event findById(Integer userId, Integer bucketId, Integer eventId) throws ResourceNotfoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, bucketId, eventId}, eventRowMapper);
        } catch (Exception e) {
            throw new ResourceNotfoundException("Event not found");
        }
    }

    @Override
    public List<Event> findAll(Integer userId, Integer bucketId) throws ResourceNotfoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, bucketId}, eventRowMapper);
    }

}
