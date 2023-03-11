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

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("")
    public ResponseEntity<Bucket> createBucket(HttpServletRequest request, @RequestBody Map<String, Object> bucketMap) {
        Integer userId = (Integer) request.getAttribute("userId");
        String name = (String) bucketMap.get("name");
        Integer stressLevel = (Integer) bucketMap.get("stressLevel");

        Bucket bucket = bucketService.createBucket(userId, name, stressLevel);
        return new ResponseEntity<>(bucket, HttpStatus.CREATED);
    }

    @GetMapping("/{bucketId}")
    public ResponseEntity<Bucket> getBucketById(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId) {
        int userId = (Integer) request.getAttribute("userId");
        Bucket bucket = bucketService.findBucketById(userId, bucketId);
        return new ResponseEntity<>(bucket, HttpStatus.OK);
    }

    @DeleteMapping("/{bucketId}")
    public ResponseEntity<Map<String, Boolean>> deleteBucket(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId) {
        int userId = (Integer) request.getAttribute("userId");
        bucketService.removeBucket(userId, bucketId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{bucketId}")
    public ResponseEntity<Map<String, Boolean>> updateBucket(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId, @RequestBody Bucket bucket){
        int userId = (Integer) request.getAttribute("userId");
        bucketService.updateBucket(userId, bucketId, bucket);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

