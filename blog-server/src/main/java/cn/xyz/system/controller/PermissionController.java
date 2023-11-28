package cn.xyz.system.controller;

import cn.xyz.system.annotation.PreAuthorize;
import cn.xyz.system.domain.Permission;
import cn.xyz.system.query.PermissionQuery;
import cn.xyz.system.service.IPermissionService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/permission")
@Api(tags = "用户接口类")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;

    /**
     * 查询所有 返回值是数组
     *
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Permission> findAll() {
    return iPermissionService.findAll();
    }

    /**
     * 单个查询 返回值是Permission
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize(name = "根据ID查询",sn = "permission:findOne",descs = "根据ID查询权限")
    public Permission findOne(@PathVariable("id") Long id) {
        return iPermissionService.findOne(id);
    }


    @PostMapping
    @PreAuthorize(name = "高级查询和分页",sn = "permission:queryPage",descs = "根据ID查询高级权限")
    public PageInfo<Permission> queryPage(@RequestBody PermissionQuery permissionQuery) {
        return iPermissionService.queryPage(permissionQuery);
    }


    @GetMapping("/dependencePermissions/{id}")
    public List<Permission> dependencePermissions(@PathVariable("id")Long id) {
        return iPermissionService.dependencePermissions(id);
    }
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iPermissionService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

}
