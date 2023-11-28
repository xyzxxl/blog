package cn.xyz.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_article")
public class Article {
   @Id
   private String _id;
   private Long id;
   private String articlePic;
   private String articleName;
   private String articleTag;
   private Long articleType;
   private Integer articleState;
   private Integer topState;
   private Long publishId;
   private Date createTime;
   //最后更新时间
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date updateTime;
   private Integer articleReadCount;
   private Integer articleStarNum;
   private Integer articleCommentNum;
   private String articleRemark;
   private String articleContent;

   //文章类型的名称
   private String articleTypeName;
   //发布人
   private String publishName;

}
