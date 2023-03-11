package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService{


    private final BucketRepository bucketRepository;
    @Autowired
    public BucketServiceImpl(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @Override
    public Bucket createBucket(Integer userId, String name, Integer stressLevel) throws BadReqestException {
        if (stressLevel > 100 || stressLevel < 0) {
            throw new BadReqestException("Starting level must be between 0 - 100");
        }

        Integer bucketId = bucketRepository.create(userId, name, stressLevel);
        return bucketRepository.findById(userId, bucketId);
    }

    @Override
    public Bucket findBucketById(Integer userId, Integer bucketId) throws ResourceNotfoundException {
        return bucketRepository.findById(userId, bucketId);
    }


    @Override
    public void removeBucket(Integer userId, Integer bucketId) throws ResourceNotfoundException {
        this.findBucketById(userId, bucketId);
        bucketRepository.removeById(userId, bucketId);
    }

    @Override
    public void updateBucket(Integer userId, Integer bucketId, Bucket bucket) throws BadReqestException {
        bucketRepository.update(userId, bucketId, bucket);
    }
}
