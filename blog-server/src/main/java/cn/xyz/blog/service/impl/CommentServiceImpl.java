package cn.xyz.blog.service.impl;

import cn.xyz.blog.domain.Comment;
import cn.xyz.blog.repository.CommentRepository;
import cn.xyz.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private Comment comment;

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    @Override
    public void add(Comment comment) {
        //没有id就是添加
        commentRepository.save(comment);
    }

    @Override
    public void delete(String id) {
//        comment.setId(id);//通过点击垃圾桶获取到的_id 被comment对象的set方法拿到了这个评论
//        commentRepository.delete(comment);//通过commentRepository删除这个对象 也就直接删除了评论
        commentRepository.deleteById(id);//第二种 调用注入的commentRepository底层方法
    }

    @Override
    public void update(Comment comment) {
        //有id就是修改
        commentRepository.save(comment);
    }

    @Override
    public void patchDelete(Long[] ids) {

    }
}
