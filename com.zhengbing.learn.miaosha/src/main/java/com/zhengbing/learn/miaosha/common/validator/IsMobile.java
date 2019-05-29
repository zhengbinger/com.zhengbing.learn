package com.zhengbing.learn.miaosha.common.validator;

import sun.reflect.annotation.AnnotationType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Target({ElementType.FIELD})
@Retention( RUNTIME )
@Documented
@Constraint(validatedBy = {IsMobileValidate.class} )
public @interface IsMobile {

    // 配置  必要  属性默认为  true
    boolean required() default true;

    // 以下三个配置项为基本配置，必须要的
    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
