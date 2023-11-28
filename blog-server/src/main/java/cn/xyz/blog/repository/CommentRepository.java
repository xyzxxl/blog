package cn.xyz.blog.repository;

import cn.xyz.blog.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//将当前接口的实例交给spring管理 -service注入使用
public interface CommentRepository extends MongoRepository<Comment,String> {
    //根据文章id查询文章的所有评论
    //findBy是一种语法格式，ArticleId不能写错，必须与Comment实体的字段对应
    List<Comment> findByArticleId(Long articleId);
}