package cn.xyz.blog.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @function:
 * @author: Tom
 * @date: 2022年12月28日 ： 9:21
 * @version: ver1.0
 * @email tangli@itsource.cn
 */
@Component
@Data
//对应集合/表t_comment ，如果集合名为comment与类名首字母一致，那么可以只写@Document，()可以省略
@Document(collection = "t_comment")
public class Comment {
    @Id //主键标识，映射的是mongodb的_id,名称一样，所以这里@Id是可以不写的
    private String id;
    @Field(name = "articleId")  //字段名称不一致，可以使用@Field指定，这里实际上是可以省略的
    //文章id - 评论的是哪一篇文章
    private Long articleId;
    //评论内容
    private String content;
    //那个用户评论的
    private Long userId;
    //评论人的昵称
    private String nickname;
    //评论的时间
    private Date createTime = new Date();
    //点赞数
    private Integer likenum = 0;
    //回复数
    private Integer replynum = 0;
    //状态：1可见，0不可见
    private Integer state;
    //父级id
    private String parentId;
}