package cn.xyz.blog.service.impl;

import cn.xyz.blog.domain.Article;
import cn.xyz.blog.dto.ArticleDto;
import cn.xyz.blog.mapper.ArticleMapper;
import cn.xyz.blog.query.ArticleQuery;
import cn.xyz.blog.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private MongoTemplate mongoTemplate;
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
    @Transactional
    public void add(Article article) {
        //添加到mysql数据库
        articleMapper.add(article);
        //添加到mongodb数据库
        mongoTemplate.save(article);
    }

    @Override
    public void delete(Long id) {
        //删除mysql的数据
        articleMapper.delete(id);
        //删除mongodb数据库中的数据
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Article.class);
    }

    @Override
    @Transactional
    public void update(Article article) {
        //修改mysql数据库数据
        article.setUpdateTime(new Date());
        articleMapper.update(article);
        //修改mongodb数据库的数据
        //1.根据id查询mongodb数据库的对象 -为了获取_id
        Query query = new Query(Criteria.where("id").is(article.getId()));
        Article mongoArticle= mongoTemplate.findOne(query, Article.class);
        //2.将_id设置到新的对象article
        article.set_id(mongoArticle.get_id());
        //3.调用save方法修改mongodb数据库
        mongoTemplate.save(article);

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

    @Override
    public List<ArticleDto> countByArticleType() {
        return articleMapper.countByArticleType();
    }

    @Override
    public List<ArticleDto> countByTime() {
        return articleMapper.countByTime();
    }

    @Override
    public List<Article> dependenceArticles() {
        return null;
    }

    @Override
    public List<Article> dependenceArticles(Long id) {
        return articleMapper.dependenceArticles(id);
    }
}
