package cn.xyz.blog.service;


import cn.xyz.blog.domain.Article;
import cn.xyz.blog.query.ArticleQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IArticleService {
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

    void patchDelete(Long[] ids);

    PageInfo<Article> queryPage(ArticleQuery articleQuery);
}
