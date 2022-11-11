package com.zizhuling.test.annotation.impl;

import com.alibaba.fastjson.JSONObject;
import com.zizhuling.test.annotation.FieldLengthValidation;
import com.zizhuling.test.utils.Message;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>
 * 字段长度校验 实现类
 * </P>
 *
 * @author hebiao Create on 2022/11/9 17:34
 * @version 1.0
 */
public class FieldLengthValidationImpl implements ConstraintValidator<FieldLengthValidation,String> {
    /**
     * 这个字段校验的异常编码
     */
    int code;

    /**
     * 最小长度
     */
    int min;
    /**
     * 最大长度
     */
    int max;
    /**
     * 填写该字段的中文名称
     */
    String message;

    @Override
    public void initialize(FieldLengthValidation constraintAnnotation) {
        this.min=constraintAnnotation.min();
        this.max=constraintAnnotation.max();
        this.message=constraintAnnotation.message();
        this.code=constraintAnnotation.code();
    }

    /**
     * @description: 业务校验
     * @author: hebiao
     * @param value
     * @param constraintValidatorContext
     * @date: 2022/11/9 17:59
     * @return: boolean
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句  
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(Message.MESSAGE, message+"长度不在范围内，应该在"+min+"到"+max+"之间。");
        jsonObject.put(Message.CODE, code);
        constraintValidatorContext.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
        if(StringUtils.isEmpty(value)){
            return false;
        }
        value=value.trim();
        return !StringUtils.isEmpty( value ) && value.length() >= min && value.length() <= max;
    }

}
