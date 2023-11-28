package cn.xyz.system.mapper;


import cn.xyz.system.domain.Logininfo;
import cn.xyz.system.domain.Menu;
import cn.xyz.system.dto.LoginDto;
import cn.xyz.system.query.LogininfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogininfoMapper {
    //查找所有
    List<Logininfo> findAll();

    //查找单个
    Logininfo findOne(Long id);

    //增加
    void add(Logininfo logininfo);

    //删除
    void delete(Long id);

    //修改
    void update(Logininfo logininfo);
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Logininfo> queryPage(LogininfoQuery logininfoQuery);

    Logininfo findByAccount(LoginDto loginDto);

    List<String> findPermissionByLogininfoId(Long id);

    List<Menu> findMenuByLogininfoId(Long id);
}
