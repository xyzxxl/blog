package cn.xyz.blog.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Param {
   private Long id;
   private String paramKey;
   private String paramValue;
   private Integer type;
   private Date createTime;
   private Date updateTime;
   private String operatorName;
   private String paramDesc;
}
