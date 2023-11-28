package cn.xyz.system.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
   private Long id;
   private String username;
   private String phone;
   private String email;
   private String salt;
   private String password;
   private Integer age;
   private Integer state;
   private Long logininfoId;

   //保存当前员工的所有角色信息
   //前端需要校色的id，所以直接装id即可
   //问题:分页列表要显示角色的名称name，回显又需要角色的id 而且这两个数据都是分页的时候查出来的
   private List<Role> dbRoles = new ArrayList<>();

   //接收前端添加和修改时 选择的角色信息
   private List<Long> roles = new ArrayList<>();




}
