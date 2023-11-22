package cn.xyz.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String realName;
    private String phone;
    private String email;
    private String salt;
    private String password;
    private String avatar;
    private String remark;
    private Long logininfoId;
}
