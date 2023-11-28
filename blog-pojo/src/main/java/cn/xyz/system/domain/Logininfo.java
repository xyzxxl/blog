package cn.xyz.system.domain;

import lombok.Data;

@Data
public class Logininfo {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String salt;
    private String password;
    private Integer type;
    private Boolean disable;







}
