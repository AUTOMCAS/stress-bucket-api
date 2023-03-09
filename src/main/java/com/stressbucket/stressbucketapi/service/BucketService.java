package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Bucket;


public interface BucketService {
    Bucket createBucket(Integer userId, String name, Integer stressLevel) throws BadReqestException;

    Bucket findBucketById(Integer userId, Integer bucketId) throws ResourceNotfoundException;

    void removeBucket(Integer userId, Integer bucketId) throws ResourceNotfoundException;

    void updateBucket(Integer userId, Integer bucketId, Bucket bucket) throws BadReqestException;
    }
