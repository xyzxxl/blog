package cn.xyz.blog.controller;


import cn.xyz.blog.domain.Article;
import cn.xyz.blog.query.ArticleQuery;
import cn.xyz.blog.service.IArticleService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
@Api(tags = "用户接口类")
public class ArticleController {
    @Autowired
    private IArticleService iArticleService;

    /**
     * 查询所有 返回值是数组
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<Article> findAll() {
    return iArticleService.findAll();
    }

    /**
     * 单个查询 返回值是Article
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Article findOne(@PathVariable("id") Long id) {
        return iArticleService.findOne(id);
    }

    /**
     * 删除 返回值是JsonResult对象
     * 除了查询 其他返回值都是JsonResult对象
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id")Long id) {
        try {
            iArticleService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param article
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody Article article) {
        try {
            if (article.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iArticleService.add(article);
                return JsonResult.success();
            } else {
                //修改
                iArticleService.update(article);
                return JsonResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PatchMapping
    //Long[] 批量删除 使用数组来接收
    public JsonResult patchDelete(@RequestBody Long[] ids) {
        try {
            iArticleService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }

    }

    @PostMapping
    public PageInfo<Article> queryPage(@RequestBody ArticleQuery articleQuery) {
        return iArticleService.queryPage(articleQuery);
    }


}
