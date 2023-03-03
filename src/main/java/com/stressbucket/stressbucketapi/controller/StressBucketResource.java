package com.stressbucket.stressbucketapi.controller;

import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/buckets")
public class StressBucketResource {

    @Autowired
    private BucketService bucketService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createBucket(@RequestBody Map<String, Object> bucketMap) {
        String bucketName = (String) bucketMap.get("bucketName");
        Integer stressLevel = (Integer) bucketMap.get("stressLevel");

        Bucket bucket = bucketService.createBucket(bucketName, stressLevel);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Bucket created");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
