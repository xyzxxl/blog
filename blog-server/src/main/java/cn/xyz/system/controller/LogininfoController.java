package cn.xyz.system.controller;


import cn.xyz.system.domain.Logininfo;
import cn.xyz.system.query.LogininfoQuery;
import cn.xyz.system.service.ILogininfoService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logininfo")
@Api(tags = "用户接口类")
public class LogininfoController {
    @Autowired
    private ILogininfoService iLogininfoService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Logininfo> findAll() {
    return iLogininfoService.findAll();
    }

    /**
     * 单个查询 返回值是Logininfo
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Logininfo findOne(@PathVariable("id") Long id) {
        return iLogininfoService.findOne(id);
    }

    /**
     * 删除 返回值是JsonResult对象
     * 除了查询 其他返回值都是JsonResult对象
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iLogininfoService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param logininfo
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Logininfo logininfo) {
        try {
            if (logininfo.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iLogininfoService.add(logininfo);
                return JsonResult.success();
            } else {
                //修改
                iLogininfoService.update(logininfo);
                return JsonResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PatchMapping
    //Long[] 批量删除 使用数组来接收
    public JsonResult patchDelete(@RequestBody Long[] ids) {
        try {
            iLogininfoService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<Logininfo> queryPage(@RequestBody LogininfoQuery logininfoQuery) {
        return iLogininfoService.queryPage(logininfoQuery);
    }
}
