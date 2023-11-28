package cn.xyz.system.mapper;


import cn.xyz.system.domain.Permission;
import cn.xyz.system.query.PermissionQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    //查找所有
    List<Permission> findAll();

    //查找单个
    Permission findOne(Long id);

    //增加
    void add(Permission permission);

    //删除
    void delete(Long id);

    //修改
    void update(Permission permission);
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Permission> queryPage(PermissionQuery permissionQuery);


    List<Permission> dependencePermissions(Long id);

    Permission selectBySn(String permissionSn);
}
