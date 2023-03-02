package com.stressbucket.stressbucketapi.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/buckets")
public class StressBucketResource {

    @PostMapping("/create")
    public String createBucket(@RequestBody Map<String, Object> bucketMap) {
        String bucketName = (String) bucketMap.get("bucketName");
        int stressLevel = (int) bucketMap.get("stressLevel");

        return bucketName + ", " + stressLevel;
    }
}
