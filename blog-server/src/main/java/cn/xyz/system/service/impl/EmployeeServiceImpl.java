package cn.xyz.system.service.impl;

import cn.xyz.system.domain.Employee;
import cn.xyz.system.domain.Role;
import cn.xyz.system.mapper.EmployeeMapper;
import cn.xyz.system.query.EmployeeQuery;
import cn.xyz.system.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
@CacheConfig(cacheNames = "employee")//将缓存名统一提到类上面
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    @CacheEvict(key = "'employeeTree'")
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(key = "'employeeTree'")
    public Employee findOne(Long id) {
        return employeeMapper.findOne(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'employeeTree'")
    public void add(Employee employee) {
        //添加员工
        employeeMapper.add(employee);//配置自增id
        //添加员工对应的角色信息
        List<Long> roles = employee.getRoles();
        if (roles.size() > 0) {//选了角色
            employeeMapper.addEmployeeRole(employee);
        }
    }

    @Override
    @Transactional
    @CacheEvict(key = "'employeeTree'")
    public void delete(Long id) {
        //删除中间表数据=关联数据
        employeeMapper.deleteRoleByEmployeeId(id);
        employeeMapper.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'employeeTree'")
    public void update(Employee employee) {
        employeeMapper.update(employee);

        //删除中间表数据=关联数据
        employeeMapper.deleteRoleByEmployeeId(employee.getId());
        //添加员工对应的角色信息
        List<Long> roles = employee.getRoles();
        if (roles.size() > 0) {//选了角色
            employeeMapper.addEmployeeRole(employee);
        }
    }

    @CacheEvict(key = "'employeeTree'")
    @Override
    public void patchDelete(Long[] ids) {
        employeeMapper.patchDelete(ids);
    }

    @Override
    public PageInfo<Employee> queryPage(EmployeeQuery employeeQuery) {
        //开启分页
        PageHelper.startPage(employeeQuery.getCurrentPage(), employeeQuery.getPageSize());
        //操作数据库
        List<Employee> employees = employeeMapper.queryPage(employeeQuery);
        for (Employee employee : employees) {
            List<Role> roles = employeeMapper.findRoleByEmployeeId(employee.getId());
            employee.setDbRoles(roles);

        }
        return new PageInfo<>(employees);
    }

    @Override
    public List<Employee> employeeTree() {
        return null;
    }


}



