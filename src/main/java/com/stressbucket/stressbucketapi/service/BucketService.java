package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Bucket;


public interface BucketService {
    Bucket createBucket(String bucketName, Integer stressLevel) throws BadReqestException;

    Bucket findBucketById(Integer bucketId) throws ResourceNotfoundException;

    void removeBucket(Integer bucketId) throws ResourceNotfoundException;

    void updateBucket(Integer bucketId, Bucket bucket) throws BadReqestException;
    }
