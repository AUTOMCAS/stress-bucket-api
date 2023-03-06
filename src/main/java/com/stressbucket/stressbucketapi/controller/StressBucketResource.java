package com.stressbucket.stressbucketapi.controller;

import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.service.BucketService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/buckets")
public class StressBucketResource {

    @Autowired
    private BucketService bucketService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> createBucket(@RequestBody Map<String, Object> bucketMap) {
        String bucketName = (String) bucketMap.get("bucketName");
        Integer stressLevel = (Integer) bucketMap.get("stressLevel");

        Bucket bucket = bucketService.createBucket(bucketName, stressLevel);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Bucket created");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{bucketId}")
    public ResponseEntity<Bucket> getBucketById(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId) {
        Bucket bucket = bucketService.findBucketById(bucketId);
        return new ResponseEntity<>(bucket, HttpStatus.OK);
    }

    @DeleteMapping("/{bucketId}")
    public ResponseEntity<Map<String, Boolean>> deleteBucket(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId) {
        bucketService.removeBucket(bucketId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{bucketId}")
    public ResponseEntity<Map<String, Boolean>> updateBucket(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId, @RequestBody Bucket bucket){
        bucketService.updateBucket(bucketId, bucket);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

