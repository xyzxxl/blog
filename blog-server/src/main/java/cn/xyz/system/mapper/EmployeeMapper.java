package cn.xyz.system.mapper;

import cn.xyz.system.domain.Employee;
import cn.xyz.system.domain.Role;
import cn.xyz.system.query.EmployeeQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
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
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Employee> queryPage(EmployeeQuery employeeQuery);

    List<Role> findRoleByEmployeeId(Long id);

    void addEmployeeRole(Employee employee);

    void deleteRoleByEmployeeId(Long id);
}
