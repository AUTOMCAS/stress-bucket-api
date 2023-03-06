package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;

public interface BucketRepository {
    Integer create(String bucketName, Integer stressLevel) throws BucketException;

    Bucket findById(Integer bucketId) throws BucketException;

    void removeById(Integer bucketId);

    void update(Integer bucketId, Bucket bucket) throws BadReqestException;

}
