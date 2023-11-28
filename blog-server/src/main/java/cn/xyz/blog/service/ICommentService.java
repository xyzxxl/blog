package cn.xyz.blog.service;


import cn.xyz.blog.domain.Comment;

import java.util.List;

/**
 * 业务接口:博客Comment
 */
public interface ICommentService {
    /**
     * 根据文章ID查询当前文章的所有评论
     * @param articleId
     * @return
     */
    List<Comment> findByArticleId(Long articleId);
    //增加
    void add(Comment comment);

    //删除
    void delete(String id);

    //修改
    void update(Comment comment);

    void patchDelete(Long[] ids);
    


}
