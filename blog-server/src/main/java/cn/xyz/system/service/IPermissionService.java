package cn.xyz.system.service;


import cn.xyz.system.domain.Permission;
import cn.xyz.system.query.PermissionQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPermissionService {
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

    void patchDelete(Long[] ids);

    PageInfo<Permission> queryPage(PermissionQuery permissionQuery);

    List<Permission> dependencePermissions(Long id);

}
