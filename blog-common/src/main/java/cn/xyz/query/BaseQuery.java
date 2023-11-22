package cn.xyz.user.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer currentPage=1;       // 当前页的号码
    private Integer pageSize=5; //一页有多少
    private String keyword;//关键字查询

}
