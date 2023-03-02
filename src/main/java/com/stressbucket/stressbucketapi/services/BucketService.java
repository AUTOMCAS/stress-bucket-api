package com.stressbucket.stressbucketapi.services;

import com.stressbucket.stressbucketapi.domain.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;


public interface BucketService {
    Bucket createBucket(String bucketName, Integer initialStressLevel) throws BucketException;
}
