package com.akira.learn.feign.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;

@RequestMapping("/test")
public interface RemoteAPI {

    @PostMapping("/post")
    @ApiOperation(value = "testPost" ,notes = "emmm")
    RemoteAPIResult testPostAPI(@RequestBody RemoteAPIParam param);

    @GetMapping("/get/{id}")
    RemoteAPIResult testGetAPI(@PathVariable("id") String id);

    @GetMapping("/get/param")
    RemoteAPIResult testGetQueryParam(@SpringQueryMap RemoteAPIParam param);
}
