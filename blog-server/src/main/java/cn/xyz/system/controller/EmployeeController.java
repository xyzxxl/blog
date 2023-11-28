package cn.xyz.system.controller;

import cn.xyz.system.annotation.PreAuthorize;
import cn.xyz.system.domain.Employee;
import cn.xyz.system.query.EmployeeQuery;
import cn.xyz.system.service.IEmployeeService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(tags = "菜单接口类")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Employee> findAll() {
    return iEmployeeService.findAll();
    }

    /**
     * 单个查询 返回值是Employee
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID查询" ,sn = "employee:get")
    @GetMapping("/{id}")
    public Employee findOne(@PathVariable("id") Long id) {
        return iEmployeeService.findOne(id);
    }

    /**
     * 删除 返回值是JsonResult对象
     * 除了查询 其他返回值都是JsonResult对象
     * @param id
     * @return
     */
    @PreAuthorize(name = "根据ID删除" ,sn = "employee:del")
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iEmployeeService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param employee
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Employee employee) {
        try {
            if (employee.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iEmployeeService.add(employee);
                return JsonResult.success();
            } else {
                //修改
                iEmployeeService.update(employee);
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
            iEmployeeService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<Employee> queryPage(@RequestBody EmployeeQuery employeeQuery) {
        return iEmployeeService.queryPage(employeeQuery);
    }

    @GetMapping("/employeeTree")
    public List<Employee> typeTree() {
        return iEmployeeService.employeeTree();
    }
}
