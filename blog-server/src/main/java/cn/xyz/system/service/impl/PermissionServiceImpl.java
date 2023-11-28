package cn.xyz.system.service.impl;


import cn.xyz.system.domain.Permission;
import cn.xyz.system.mapper.PermissionMapper;
import cn.xyz.system.query.PermissionQuery;
import cn.xyz.system.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public Permission findOne(Long id) {
        return permissionMapper.findOne(id);
    }

    @Override
    @Transactional
    public void add(Permission permission) {
        //添加到mysql数据库
        permissionMapper.add(permission);
        //添加到mongodb数据库
        mongoTemplate.save(permission);
    }

    @Override
    public void delete(Long id) {
        //删除mysql的数据
        permissionMapper.delete(id);
     }

    @Override
    @Transactional
    public void update(Permission permission) {
        //修改mysql数据库数据

        permissionMapper.update(permission);
        //修改mongodb数据库的数据
        //1.根据id查询mongodb数据库的对象 -为了获取_id
        Query query = new Query(Criteria.where("id").is(permission.getId()));
        Permission mongoPermission= mongoTemplate.findOne(query, Permission.class);
        //2.将_id设置到新的对象permission

        //3.调用save方法修改mongodb数据库
        mongoTemplate.save(permission);

    }

    @Override
    public void patchDelete(Long[] ids) {
        permissionMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<Permission> queryPage(PermissionQuery permissionQuery) {
        //开启分页
        PageHelper.startPage(permissionQuery.getCurrentPage(), permissionQuery.getPageSize());
        //操作数据库
        List<Permission> data = permissionMapper.queryPage(permissionQuery);
        return new PageInfo<>(data);
    }


    @Override
    public List<Permission> dependencePermissions(Long id) {
        return permissionMapper.dependencePermissions(id);
    }
}
