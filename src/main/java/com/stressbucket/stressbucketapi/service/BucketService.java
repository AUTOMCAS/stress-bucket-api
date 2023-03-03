package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;


public interface BucketService {
    Bucket createBucket(String bucketName, Integer stressLevel) throws BucketException;
}
