package com.akira.learn.sc.gw.eureka.controller;

import com.akira.learn.feign.api.RemoteAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;

@RestController
public class TestController implements RemoteAPI {
    protected final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Override
    public RemoteAPIResult testPostAPI(RemoteAPIParam param) {
        return getRemoteAPIResult(param);
    }

    @Override
    public RemoteAPIResult testGetAPI(String id) {
        logger.info("start testGetAPI");
        RemoteAPIResult result = new RemoteAPIResult();
        result.setName(id);
        return result;
    }

    @Override
    public RemoteAPIResult testGetQueryParam(RemoteAPIParam param) {
        logger.info("start testGetQueryParam");
        return getRemoteAPIResult(param);
    }

    private RemoteAPIResult getRemoteAPIResult(RemoteAPIParam param) {
        logger.warn("start getRemoteAPIResult");
        RemoteAPIResult result = new RemoteAPIResult();
        result.setDate(param.getDate());
        result.setInnerObject(param.getInnerObject());
        result.setName(param.getName());
        result.setNumber(param.getNumber());
        return result;
    }

}
