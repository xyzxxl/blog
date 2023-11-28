package cn.xyz.system.service.impl;

import cn.xyz.system.domain.Menu;
import cn.xyz.system.mapper.MenuMapper;
import cn.xyz.system.query.MenuQuery;
import cn.xyz.system.service.IMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
@CacheConfig(cacheNames = "menu")//将缓存名统一提到类上面
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    @CacheEvict(key = "'menuTree'")
    public List<Menu> findAll() {
        return menuMapper.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(key = "'menuTree'")
    public Menu findOne(Long id) {
        return menuMapper.findOne(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'menuTree'")
    public void add(Menu menu) {
        menuMapper.add(menu);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'menuTree'")
    public void delete(Long id) {
        menuMapper.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "'menuTree'")
    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    @CacheEvict(key = "'menuTree'")
    @Override
    public void patchDelete(Long[] ids) {
        menuMapper.patchDelete(ids);
    }

    @Override
    public PageInfo<Menu> queryPage(MenuQuery menuQuery) {
        //开启分页
        PageHelper.startPage(menuQuery.getCurrentPage(), menuQuery.getPageSize());
        //操作数据库
        List<Menu> data = menuMapper.queryPage(menuQuery);
        return new PageInfo<>(data);
    }

    @Cacheable(key = "'menuTree'")
    @Override
    public List<Menu> menuTree() {
        return getMenuTree();
    }

    //从mysql数据库查询菜单树
    private List<Menu> getMenuTree() {
        List<Menu> menuTree = new ArrayList<>();

        List<Menu> menus = menuMapper.findAll();
        Map<Long, Menu> map = new HashMap<>();
        for (Menu menu : menus) {
            map.put(menu.getId(), menu);
        }

        //遍历所有的文章类型
        for (Menu menu : menus) {
            if (menu.getParentId() == null) {//顶级类型
                menuTree.add(menu);
            }else{//有父类型
                Long parentId = menu.getParentId();

                Menu parentMenu = map.get(parentId);
                if (parentMenu != null) {
                    List<Menu> children = parentMenu.getChildren();
                    children.add(menu);
                }
            }
        }
        return menuTree;
    }
}
