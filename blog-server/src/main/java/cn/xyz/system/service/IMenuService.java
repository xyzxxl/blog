package cn.xyz.system.service;

import cn.xyz.system.domain.Menu;
import cn.xyz.system.query.MenuQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMenuService {
    //查找所有
    List<Menu> findAll();

    //查找单个
    Menu findOne(Long id);

    //增加
    void add(Menu menu);

    //删除
    void delete(Long id);

    //修改
    void update(Menu menu);

    void patchDelete(Long[] ids);

    PageInfo<Menu> queryPage(MenuQuery menuQuery);

    List<Menu> menuTree();
}
