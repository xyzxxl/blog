package cn.xyz.blog.mapper;


import cn.xyz.blog.domain.Param;
import cn.xyz.blog.query.ParamQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParamMapper {
    //查找所有
    List<Param> findAll();

    //查找单个
    Param findOne(Long id);

    //增加
    void add(Param param);

    //删除
    void delete(String id);

    //修改
    void update(Param param);
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Param> queryPage(ParamQuery paramQuery);


}
