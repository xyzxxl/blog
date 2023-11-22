package cn.xyz.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleType {
  private Long id;
  private String  typeName;
  private Integer  status;
  //创建时间
  //当前字段转换为json格式数据时格式化，格式pattern=年[year]-月[month]-日[day]
  //timezone时区，中国在时间标准事件GMT的东八区，也叫+8区
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date  createTime = new Date();
  //修改时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date updateTime;
  private Long  parentId;

  //保存父类型的名称
  private String parentTypeName;
  //添加一个集合，保存自己的所有子类型
  private List<ArticleType> children = new ArrayList<>();

}
