package cn.xyz.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
   private Long id;
   private String name;
   private String component;
   private String url;
   private String icon;
   private Integer index;
   private Long parentId;

   //上级菜单对象
   private String parentMenuName;

   //添加一个集合，保存自己的所有子类型
   @JsonInclude(JsonInclude.Include.NON_EMPTY)//类型树最后一级没有数据就不显示
   private List<Menu> children = new ArrayList<>();

}
