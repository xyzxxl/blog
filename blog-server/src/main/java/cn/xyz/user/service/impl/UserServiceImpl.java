package cn.xyz.user.service.impl;

import cn.xyz.user.domain.User;
import cn.xyz.user.mapper.UserMapper;
import cn.xyz.user.query.UserQuery;
import cn.xyz.user.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userMapper.findOne(id);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void patchDelete(Long[] ids) {
        userMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<User> queryPage(UserQuery userQuery) {
        //开启分页
        PageHelper.startPage(userQuery.getCurrentPage(), userQuery.getPageSize());
        //操作数据库
        List<User> data = userMapper.queryPage(userQuery);
        return new PageInfo<>(data);
    }
}
