package cn.xyz.blog.mapper;


import cn.xyz.blog.domain.Article;
import cn.xyz.blog.query.ArticleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //查找所有
    List<Article> findAll();

    //查找单个
    Article findOne(Long id);

    //增加
    void add(Article article);

    //删除
    void delete(Long id);

    //修改
    void update(Article article);
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<Article> queryPage(ArticleQuery articleQuery);
}
