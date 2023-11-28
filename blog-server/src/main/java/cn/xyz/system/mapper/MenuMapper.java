package cn.xyz.system.mapper;

import cn.xyz.system.domain.Menu;
import cn.xyz.system.query.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MenuMapper {
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
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Menu> queryPage(MenuQuery menuQuery);
}
