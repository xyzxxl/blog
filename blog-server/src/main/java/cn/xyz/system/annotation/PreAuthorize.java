package cn.xyz.system.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PreAuthorize {
    //对应
    String name();

    String sn();

    String descs() default "";
}
