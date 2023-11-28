package cn.xyz.blog.controller;


import cn.xyz.blog.domain.Comment;
import cn.xyz.blog.service.ICommentService;
import cn.xyz.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
@Api(tags = "评论接口类")
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    /**
     * 根据文章id查询当前文章的所有评论
     *
     * @param articleId
     * @return
     */
    @GetMapping("/{articleId}")
    public List<Comment> getComment(@PathVariable("articleId") Long articleId) {
        return iCommentService.findByArticleId(articleId);
    }

    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Comment comment) {
        try {
            if (comment.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iCommentService.add(comment);
            } else {
                //修改
                iCommentService.update(comment);
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id") String id) {
        try {
            iCommentService.delete(id);
            return JsonResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }
}
