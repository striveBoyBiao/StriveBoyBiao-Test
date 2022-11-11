package com.zizhuling.test.annotation;

import com.zizhuling.test.annotation.impl.FieldLengthValidationImpl;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 字段长度校验
 * </P>
 * @author hebiao Create on 2022/11/9 17:34
 * @version 1.0
 */
@Constraint( validatedBy = FieldLengthValidationImpl.class)
@Retention( RetentionPolicy.RUNTIME )
@Target( {ElementType.FIELD,ElementType.METHOD} )
public @interface FieldLengthValidation {

    String value();
    /**
     * 这个字段校验的异常编码
     */
    int code();

    /**
     * 最小长度
     */
    int min() default 0;
    /**
     * 最大长度
     */
    int max() default Integer.MAX_VALUE;
    /**
     * 填写该字段的中文名称
     */
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
