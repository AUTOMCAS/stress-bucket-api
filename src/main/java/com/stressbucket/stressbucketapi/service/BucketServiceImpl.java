package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.exceptions.BucketException;
import com.stressbucket.stressbucketapi.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService{

    @Autowired
    private BucketRepository bucketRepository;

    @Override
    public Bucket createBucket(String bucketName, Integer stressLevel) throws BucketException {
        if (stressLevel > 100 || stressLevel < 0) {
            throw new BucketException("Starting level must be between 0 - 100");
        }

        Integer bucketId = bucketRepository.create(bucketName, stressLevel);
        return bucketRepository.findById(bucketId);
    }

    @Override
    public Bucket findBucketById(Integer bucketId) throws ResourceNotfoundException {
        return bucketRepository.findById(bucketId);
    }


    @Override
    public void removeBucket(Integer bucketId) throws ResourceNotfoundException {
        this.findBucketById(bucketId);
        bucketRepository.removeById(bucketId);
    }
}
