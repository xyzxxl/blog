package cn.xyz.system.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String account;
    private String password;
    private Integer type;



}
