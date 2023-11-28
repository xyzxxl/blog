package cn.xyz.system.controller;

import cn.xyz.exception.BusinessException;
import cn.xyz.system.dto.LoginDto;
import cn.xyz.system.service.ILogininfoService;
import cn.xyz.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILogininfoService iLogininfoService;

    @PostMapping("/account")
    public JsonResult accountLogin(@RequestBody LoginDto loginDto) {
        try {
            String jwtToken = iLogininfoService.accountLogin(loginDto);
            return JsonResult.success(jwtToken);
        } catch (BusinessException e) {
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("网络繁忙，一会儿再试");
        }
    }
}
