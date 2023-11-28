package cn.xyz.system.service;

import cn.xyz.system.domain.Employee;
import cn.xyz.system.query.EmployeeQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    //查找所有
    List<Employee> findAll();

    //查找单个
    Employee findOne(Long id);

    //增加
    void add(Employee employee);

    //删除
    void delete(Long id);

    //修改
    void update(Employee employee);

    void patchDelete(Long[] ids);

    PageInfo<Employee> queryPage(EmployeeQuery employeeQuery);

    List<Employee> employeeTree();
}
