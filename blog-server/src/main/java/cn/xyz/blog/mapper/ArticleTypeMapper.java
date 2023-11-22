package cn.xyz.blog.mapper;

import cn.xyz.blog.domain.ArticleType;
import cn.xyz.blog.query.ArticleTypeQuery;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ArticleTypeMapper {
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
    //批量删除
    void patchDelete(Long[] ids);
    //操作分页
    List<ArticleType> queryPage(ArticleTypeQuery articleTypeQuery);
}
