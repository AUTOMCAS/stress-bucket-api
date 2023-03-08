package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.model.Bucket;

public interface BucketRepository {
    Integer create(Integer userId, String name, Integer stressLevel) throws BadReqestException;

    Bucket findById(Integer bucketId) throws BadReqestException;

    void removeById(Integer bucketId);

    void update(Integer bucketId, Bucket bucket) throws BadReqestException;

}
