package cn.xyz.blog.service;


import cn.xyz.blog.domain.Param;
import cn.xyz.blog.query.ParamQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IParamService {
    //查找所有
    List<Param> findAll();

    //查找单个
    Param findOne(Long id);

    //增加
    void add(Param param);

    //删除
    void delete(Long id);

    //修改
    void update(Param param);

    void patchDelete(Long[] ids);

    PageInfo<Param> queryPage(ParamQuery paramQuery);


    Map<String, Object> getParams();
}
