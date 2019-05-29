package com.zhengbing.learn.miaosha.common.validator;

import com.zhengbing.learn.miaosha.util.ValidateUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
public class IsMobileValidate implements ConstraintValidator<IsMobile,String> {

    boolean required = false;

    @Override
    public void initialize( IsMobile constraintAnnotation ) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid( String mobile, ConstraintValidatorContext constraintValidatorContext ) {
        if ( required ){
            return ValidateUtil.isMobile( mobile );
        } else {
            if ( StringUtils.isEmpty( mobile ) ) {
                return true;
            } else {
                return ValidateUtil.isMobile( mobile );
            }
        }
    }
}
