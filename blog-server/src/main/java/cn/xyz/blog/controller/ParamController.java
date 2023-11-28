package cn.xyz.blog.controller;


import cn.xyz.blog.service.IParamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/param")
@Api(tags = "用户接口类")
public class ParamController {
    @Autowired
    private IParamService iParamService;

    @GetMapping("/getParams")
    public Map<String,Object> getParams(){
        return iParamService.getParams();
    }


}
