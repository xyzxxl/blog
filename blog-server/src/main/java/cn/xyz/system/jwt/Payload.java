package cn.xyz.system.jwt;


import cn.xyz.system.domain.Logininfo;
import cn.xyz.system.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 登录信息对象：基本信息、资源权限信息、菜单权限信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    // 1.登录基本信息
    private Logininfo logininfo;

    // 2.登录资源权限信息，前端只需要权限的sn编码
    private List<String> permissions;

    // 3.登录菜单权限信息
    private List<Menu> menus;
}