package cn.xyz.blog.service.impl;


import cn.xyz.blog.domain.Article;
import cn.xyz.blog.mapper.ArticleMapper;
import cn.xyz.blog.query.ArticleQuery;
import cn.xyz.blog.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public Article findOne(Long id) {
        return articleMapper.findOne(id);
    }

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public void delete(Long id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void patchDelete(Long[] ids) {
        articleMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<Article> queryPage(ArticleQuery articleQuery) {
        //开启分页
        PageHelper.startPage(articleQuery.getCurrentPage(), articleQuery.getPageSize());
        //操作数据库
        List<Article> data = articleMapper.queryPage(articleQuery);
        return new PageInfo<>(data);
    }
}
