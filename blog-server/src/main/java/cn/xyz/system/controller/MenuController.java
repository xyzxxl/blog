package cn.xyz.system.controller;

import cn.xyz.system.annotation.PreAuthorize;
import cn.xyz.system.domain.Menu;
import cn.xyz.system.query.MenuQuery;
import cn.xyz.system.service.IMenuService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单接口类")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Menu> findAll() {
    return iMenuService.findAll();
    }

    /**
     * 单个查询 返回值是Menu
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID查询" ,sn = "menu:get")
    @GetMapping("/{id}")
    public Menu findOne(@PathVariable("id") Long id) {
        return iMenuService.findOne(id);
    }

    /**
     * 删除 返回值是JsonResult对象
     * 除了查询 其他返回值都是JsonResult对象
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID删除" ,sn = "menu:del")
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iMenuService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param menu
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Menu menu) {
        try {
            if (menu.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iMenuService.add(menu);
                return JsonResult.success();
            } else {
                //修改
                iMenuService.update(menu);
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
            iMenuService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<Menu> queryPage(@RequestBody MenuQuery menuQuery) {
        return iMenuService.queryPage(menuQuery);
    }

    @GetMapping("/menuTree")
    public List<Menu> typeTree() {
        return iMenuService.menuTree();
    }
}
