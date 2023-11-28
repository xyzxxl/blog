package cn.xyz.system.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Role {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标号
     */
    private String sn;

    //功能1:将后端查询的权限和菜单传递到前端
    //功能2:前端选择了权限和菜单的时候，可以将前端数据传递给后端，操作数据库
    private List<Long> permissions = new ArrayList<>();
    private List<Long> menus = new ArrayList<>();
}
