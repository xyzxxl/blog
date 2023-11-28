package cn.xyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//声明当前类是一个SpringBoot项目的启动类
@SpringBootApplication
//扫描Mapper接口的包下所有的Mapper接口，生成这些接口的代理对象，供业务Service注入使用
@MapperScan("cn.xyz.**.mapper")
@EnableCaching
public class BlogApp {
    public static void main(String[] args) {
        //使用启动类去启动或运行Spring程序
        SpringApplication.run(BlogApp.class,args);
    }
}