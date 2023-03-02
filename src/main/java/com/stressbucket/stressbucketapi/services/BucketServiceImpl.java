package com.stressbucket.stressbucketapi.services;

import com.stressbucket.stressbucketapi.domain.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;
import com.stressbucket.stressbucketapi.repositories.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService{

    @Autowired
    BucketRepository bucketRepository;

    @Override
    public Bucket createBucket(String bucketName, Integer initialStressLevel) throws BucketException {
        if (initialStressLevel > 100 || initialStressLevel < 0) {
            throw new BucketException("Starting level must be between 0 - 100");
        }

        Integer bucketId = bucketRepository.create(bucketName, initialStressLevel);
        return bucketRepository.findById(bucketId);
    }
}
