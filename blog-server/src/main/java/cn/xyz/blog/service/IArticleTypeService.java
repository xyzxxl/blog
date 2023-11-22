package cn.xyz.blog.service;

import cn.xyz.blog.domain.ArticleType;
import cn.xyz.blog.query.ArticleTypeQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IArticleTypeService {
    //查找所有
    List<ArticleType> findAll();

    //查找单个
    ArticleType findOne(Long id);

    //增加
    void add(ArticleType articleType);

    //删除
    void delete(Long id);

    //修改
    void update(ArticleType articleType);

    void patchDelete(Long[] ids);

    PageInfo<ArticleType> queryPage(ArticleTypeQuery articleTypeQuery);

    List<ArticleType> typeTree();
}
