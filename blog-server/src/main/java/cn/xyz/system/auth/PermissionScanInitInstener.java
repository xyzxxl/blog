package cn.xyz.system.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义注解监听器，注解生效就是依靠此类
 * CommandLineRunner接口的作用是在Spring Boot应用程序启动后运行一些代码
 **/
@Slf4j
@Component
public class PermissionScanInitInstener implements CommandLineRunner {

  @Autowired
  private PermissionScan permissionScan;

  @Override
  public void run(String... args) throws Exception {
    // 为了防止我们的代码比较复杂影响项目启动速度，我们创建线程来运行
    new Thread(new Runnable(){
      @Override
      public void run() {
        log.info("SpringBoot listener start...");
        // 执行permission数据添加方法
        permissionScan.scanPermission();
        log.info("SpringBoot listener stop...");
      }
    }).start();
  }
}