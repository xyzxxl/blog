package cn.xyz.user.controller;

import cn.xyz.user.domain.User;
import cn.xyz.user.query.UserQuery;
import cn.xyz.user.service.IUserService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口类")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<User> findAll() {
    return iUserService.findAll();
    }

    /**
     * 单个查询 返回值是User
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id) {
        return iUserService.findOne(id);
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
            iUserService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param user
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody User user) {
        try {
            if (user.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iUserService.add(user);
                return JsonResult.success();
            } else {
                //修改
                iUserService.update(user);
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
            iUserService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<User> queryPage(@RequestBody UserQuery userQuery) {
        return iUserService.queryPage(userQuery);
    }
}
