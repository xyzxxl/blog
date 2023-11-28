package cn.xyz.system.service.impl;

import cn.xyz.system.domain.Role;
import cn.xyz.system.mapper.RoleMapper;
import cn.xyz.system.query.RoleQuery;
import cn.xyz.system.service.IRoleService;
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
@CacheConfig(cacheNames = "role")//将缓存名统一提到类上面
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    @CacheEvict(key = "'roleTree'")
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(key = "'roleTree'")
    public Role findOne(Long id) {
        return roleMapper.findOne(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'roleTree'")
    public void add(Role role) {
        //添加角色信息
        roleMapper.add(role);//自增id
        //添加关联表信息
        List<Long> permissions = role.getPermissions();
        List<Long> menus = role.getMenus();
        if (permissions.size() > 0) {
            roleMapper.addRolePermission(role);
        }
        if (menus.size() > 0) {
            roleMapper.addRoleMenu(role);
        }

    }

    @Override
    @Transactional
    @CacheEvict(key = "'roleTree'")
    public void delete(Long id) {
        //删除关联表信息
        roleMapper.deletePermissionByRoleId(id);
        roleMapper.deleteMenuByRoleId(id);
        //删除角色
        roleMapper.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'roleTree'")
    public void update(Role role) {
        //删除关联表信息
        roleMapper.deletePermissionByRoleId(role.getId());
        roleMapper.deleteMenuByRoleId(role.getId());
        //添加关联表信息
        List<Long> permissions = role.getPermissions();
        List<Long> menus = role.getMenus();
        if (permissions.size() > 0) {
            roleMapper.addRolePermission(role);
        }
        if (menus.size() > 0) {
            roleMapper.addRoleMenu(role);
        }
        roleMapper.update(role);
    }

    @CacheEvict(key = "'roleTree'")
    @Override
    public void patchDelete(Long[] ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    @Override
    public PageInfo<Role> queryPage(RoleQuery roleQuery) {
        //开启分页
        PageHelper.startPage(roleQuery.getCurrentPage(), roleQuery.getPageSize());
        //操作数据库
        List<Role> data = roleMapper.queryPage(roleQuery);
        return new PageInfo<>(data);
    }

    @Override
    public List<Role> roleTree() {
        return null;
    }


}
