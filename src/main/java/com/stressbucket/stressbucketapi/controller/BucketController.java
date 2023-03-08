package com.stressbucket.stressbucketapi.controller;

import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.service.BucketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/buckets")
public class BucketController {

    @Autowired
    private BucketService bucketService;

    @PostMapping("")
    public ResponseEntity<Bucket> createBucket(@RequestBody Map<String, Object> bucketMap) {
        Integer userId = (Integer) bucketMap.get("userId");
        String name = (String) bucketMap.get("name");
        Integer stressLevel = (Integer) bucketMap.get("stressLevel");

        Bucket bucket = bucketService.createBucket(userId, name, stressLevel);
        return new ResponseEntity<>(bucket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bucket> getBucketById(HttpServletRequest request, @PathVariable("id") Integer bucketId) {
        Bucket bucket = bucketService.findBucketById(bucketId);
        return new ResponseEntity<>(bucket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBucket(HttpServletRequest request, @PathVariable("id") Integer bucketId) {
        bucketService.removeBucket(bucketId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> updateBucket(HttpServletRequest request, @PathVariable("id") Integer bucketId, @RequestBody Bucket bucket){
        bucketService.updateBucket(bucketId, bucket);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

