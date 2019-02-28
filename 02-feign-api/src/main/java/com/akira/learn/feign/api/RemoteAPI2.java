package com.akira.learn.feign.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("date")
@Api(tags = "远程API2")
public interface RemoteAPI2 {
    @GetMapping("date")
    @ApiOperation(value = "测试日期格式化",notes = "哟吼吼")
    Date dateTest(@RequestParam("date") @ApiParam(name = "日期") Date date);
}
