package com.stressbucket.stressbucketapi.repositories;

import com.stressbucket.stressbucketapi.domain.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;

public interface BucketRepository {
    Integer create(String bucketName, Integer stressLevel) throws BucketException;

    Bucket findByName(String bucketName) throws BucketException;

    Bucket findById(Integer bucketId) throws BucketException;

}
