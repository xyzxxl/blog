package cn.xyz.user.service;

import cn.xyz.user.domain.User;
import cn.xyz.user.query.UserQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {
    //查找所有
    List<User> findAll();

    //查找单个
    User findOne(Long id);

    //增加
    void add(User user);

    //删除
    void delete(Long id);

    //修改
    void update(User user);

    void patchDelete(Long[] ids);

    PageInfo<User> queryPage(UserQuery userQuery);
}
