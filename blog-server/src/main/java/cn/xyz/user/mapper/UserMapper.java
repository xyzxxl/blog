package cn.xyz.user.mapper;

import cn.xyz.user.domain.User;
import cn.xyz.user.query.UserQuery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
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
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<User> queryPage(UserQuery userQuery);
}
