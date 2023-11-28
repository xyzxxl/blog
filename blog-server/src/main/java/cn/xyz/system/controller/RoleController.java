package cn.xyz.system.controller;

import cn.xyz.system.annotation.PreAuthorize;
import cn.xyz.system.domain.Role;
import cn.xyz.system.query.RoleQuery;
import cn.xyz.system.service.IRoleService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "菜单接口类")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Role> findAll() {
    return iRoleService.findAll();
    }

    /**
     * 单个查询 返回值是Role
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID查询" ,sn = "role:get")
    @GetMapping("/{id}")
    public Role findOne(@PathVariable("id") Long id) {
        return iRoleService.findOne(id);
    }

    /**
     * 删除 返回值是JsonResult对象
     * 除了查询 其他返回值都是JsonResult对象
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID删除" ,sn = "role:del")
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iRoleService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param role
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Role role) {
        try {
            if (role.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iRoleService.add(role);
                return JsonResult.success();
            } else {
                //修改
                iRoleService.update(role);
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
            iRoleService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<Role> queryPage(@RequestBody RoleQuery roleQuery) {
        return iRoleService.queryPage(roleQuery);
    }

    @GetMapping("/roleTree")
    public List<Role> typeTree() {
        return iRoleService.roleTree();
    }
}
