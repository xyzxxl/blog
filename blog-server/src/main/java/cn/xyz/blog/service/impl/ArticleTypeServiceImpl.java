package cn.xyz.blog.service.impl;

import cn.xyz.blog.domain.ArticleType;
import cn.xyz.blog.mapper.ArticleTypeMapper;
import cn.xyz.blog.query.ArticleTypeQuery;
import cn.xyz.blog.service.IArticleTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
@CacheConfig(cacheNames = "articleType")//将缓存名统一提到类上面
public class ArticleTypeServiceImpl implements IArticleTypeService {
    @Autowired
    private ArticleTypeMapper articleTypeMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<ArticleType> findAll() {
        return articleTypeMapper.findAll();
    }

    @Override
    public ArticleType findOne(Long id) {
        return articleTypeMapper.findOne(id);
    }

    @Override
    public void add(ArticleType articleType) {
        articleTypeMapper.add(articleType);
    }

    @Override
    public void delete(Long id) {
        articleTypeMapper.delete(id);
    }

    @Override
    public void update(ArticleType articleType) {
        articleType.setUpdateTime(new Date());
        articleTypeMapper.update(articleType);
    }

    @Override
    public void patchDelete(Long[] ids) {
        articleTypeMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<ArticleType> queryPage(ArticleTypeQuery articleTypeQuery) {
        //开启分页
        PageHelper.startPage(articleTypeQuery.getCurrentPage(), articleTypeQuery.getPageSize());
        //操作数据库
        List<ArticleType> data = articleTypeMapper.queryPage(articleTypeQuery);
        return new PageInfo<>(data);
    }

    private List<ArticleType> getTypeTree() {
        List<ArticleType> typeTree = new ArrayList<>();

        List<ArticleType> types = articleTypeMapper.findAll();

        for (ArticleType type : types) {
            if (type.getParentId() == null) {//顶级类型
                typeTree.add(type);
            }else{//有父类型
                Long parentId = type.getParentId();
                //通过id获取当前对象:???????
                ArticleType parentType = articleTypeMapper.findOne(parentId);
                if (parentType != null) {
                    List<ArticleType> children = parentType.getChildren();
                    children.add(type);
                }
            }
        }
        return typeTree;
    }

    @Override
    public List<ArticleType> typeTree() {
       //1.直接从redis去拿
        Object obj = redisTemplate.opsForValue().get("typeTree");
        if (obj == null) {
            List<ArticleType> typeTree = getTypeTree();
            redisTemplate.opsForValue().set("typeTree", typeTree);
            return typeTree;
        }else {
            return (List<ArticleType>) obj;
        }
        }
}
