package com.akira.learn.sc.gw.feign.controller;

import com.akira.learn.feign.vo.InnerObject;
import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;
import com.akira.learn.sc.gw.feign.service.HiService;
import com.akira.learn.sc.gw.feign.service.TestService;
import com.akira.learn.sc.gw.feign.service.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestService2 testService2;

    @GetMapping("/hi")
    public String sayHi(@RequestParam("name") String name) {
        return hiService.sayHiFromHiService(name);
    }

    @GetMapping("/testpost")
    public RemoteAPIResult testPost() {
        RemoteAPIParam param = new RemoteAPIParam();
        param.setName("中文");
        param.setDate(new Date());
        InnerObject innerObject = new InnerObject();
        innerObject.setInnerName("ok");
        param.setInnerObject(innerObject);
        param.setNumber(2);
        return testService.testPostAPI(param);
    }

    @GetMapping("/testpath")
    public RemoteAPIResult testPathParam() {
        return testService.testGetAPI("中文");
    }

    @GetMapping("/testquery")
    public RemoteAPIResult testQueryParm() {
        RemoteAPIParam param = new RemoteAPIParam();
        param.setName("中文");
        param.setNumber(1);
        //get 暂不支持对象嵌套，因为无法设置QueryMapEncoder,默认的是 FieldQueryMapEncoder
//        InnerObject innerObject = new InnerObject();
//        innerObject.setInnerName("inner");
//        param.setInnerObject(innerObject);
        return testService.testGetQueryParam(param);
    }

    @GetMapping("/testDate")
    public Date testDate(@RequestParam("date") Date date) {
        return testService2.dateTest(date);
    }
}
