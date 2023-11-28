package cn.xyz.blog.dto;

import lombok.Data;

@Data
public class ArticleDto {
    /**
     * 文章分类ID
     */
    private Long articleTypeId;
    /**
     * 文章类型
     */
    private String articleTypeName;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 时间
     */
    private String time;
    /**
     * 数量
     */
    private Integer count;
}
