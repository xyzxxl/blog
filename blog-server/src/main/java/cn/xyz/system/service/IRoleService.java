package cn.xyz.system.service;

import cn.xyz.system.domain.Role;
import cn.xyz.system.query.RoleQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
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

    void patchDelete(Long[] ids);

    PageInfo<Role> queryPage(RoleQuery roleQuery);

    List<Role> roleTree();
}
