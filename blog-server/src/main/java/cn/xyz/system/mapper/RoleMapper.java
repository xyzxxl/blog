package cn.xyz.system.mapper;

import cn.xyz.system.domain.Role;
import cn.xyz.system.query.RoleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    //查找所有
    List<Role> findAll();

    //查找单个
    Role findOne(Long id);

    //增加
    void add(Role role);

    //删除
    void delete(Long id);

    //修改
    void update(Role role);
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Role> queryPage(RoleQuery roleQuery);

    void addRolePermission(Role role);

    void addRoleMenu(Role role);

    void deletePermissionByRoleId(Long id);

    void deleteMenuByRoleId(Long id);
}
