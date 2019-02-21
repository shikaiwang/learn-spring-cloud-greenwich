package com.akira.learn.sc.gw.eureka;

import com.akira.learn.feign.api.RemoteAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;

@RestController
public class TestController implements RemoteAPI {

    @Override
    public RemoteAPIResult testPostAPI(RemoteAPIParam param) {
        return getRemoteAPIResult(param);
    }

    @Override
    public RemoteAPIResult testGetAPI(String id) {
        RemoteAPIResult result = new RemoteAPIResult();
        result.setName(id);
        return result;
    }

    @Override
    public RemoteAPIResult testGetQueryParam(RemoteAPIParam param) {
        return getRemoteAPIResult(param);
    }

    private RemoteAPIResult getRemoteAPIResult(RemoteAPIParam param) {
        RemoteAPIResult result = new RemoteAPIResult();
        result.setDate(param.getDate());
        result.setInnerObject(param.getInnerObject());
        result.setName(param.getName());
        result.setNumber(param.getNumber());
        return result;
    }

}
