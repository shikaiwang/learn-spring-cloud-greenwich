package com.akira.learn.feign.api;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;

@RequestMapping("/test")
public interface RemoteAPI {

    @PostMapping("/post")
    RemoteAPIResult testPostAPI(@RequestBody RemoteAPIParam param);

    @GetMapping("/get/{id}")
    RemoteAPIResult testGetAPI(@PathVariable("id") String id);

    @GetMapping("/get/param")
    RemoteAPIResult testGetQueryParam(@SpringQueryMap RemoteAPIParam param);
}
