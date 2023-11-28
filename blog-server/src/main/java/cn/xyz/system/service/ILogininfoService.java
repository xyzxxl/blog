package cn.xyz.system.service;

import cn.xyz.system.domain.Logininfo;
import cn.xyz.system.dto.LoginDto;
import cn.xyz.system.query.LogininfoQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ILogininfoService {
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

    void patchDelete(Long[] ids);

    PageInfo<Logininfo> queryPage(LogininfoQuery logininfoQuery);

    String accountLogin(LoginDto loginDto);
}
