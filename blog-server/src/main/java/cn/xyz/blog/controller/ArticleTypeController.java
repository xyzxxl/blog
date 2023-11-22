package cn.xyz.blog.controller;


import cn.xyz.blog.domain.ArticleType;
import cn.xyz.blog.query.ArticleTypeQuery;
import cn.xyz.blog.service.IArticleTypeService;
import cn.xyz.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleType")
@Api(tags = "文章类型接口类")
public class ArticleTypeController {
    @Autowired
    private IArticleTypeService iArticleTypeService;

    /**
     * 查询所有 返回值是数组
     *
     * @return
     */
    @ApiOperation(value = "查询所有接口")
    //必须要在类上加上@ApiOperation注解才能看到结果
    @GetMapping
    public List<ArticleType> findAll() {
    return iArticleTypeService.findAll();
    }

    /**
     * 单个查询 返回值是ArticleType
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ArticleType findOne(@PathVariable("id") Long id) {
        return iArticleTypeService.findOne(id);
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
            iArticleTypeService.delete(id);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 修改和新增
     * @param articleType
     * @return
     */
    @PutMapping
    public JsonResult addOrUpdate(@RequestBody ArticleType articleType) {
        try {
            if (articleType.getId() == null) {
                //添加 添加没有传id 所以可以判断
                iArticleTypeService.add(articleType);
                return JsonResult.success();
            } else {
                //修改
                iArticleTypeService.update(articleType);
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
            iArticleTypeService.patchDelete(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @PostMapping
    public PageInfo<ArticleType> queryPage(@RequestBody ArticleTypeQuery articleTypeQuery) {
        return iArticleTypeService.queryPage(articleTypeQuery);
    }

    @GetMapping("/typeTree")
    public List<ArticleType> typeTree() {
        return iArticleTypeService.typeTree();
    }
}
