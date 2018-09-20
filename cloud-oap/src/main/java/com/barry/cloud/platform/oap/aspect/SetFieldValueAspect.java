package com.barry.cloud.platform.oap.aspect;

import com.barry.cloud.platform.oap.utils.BeanFieldsUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/18 21:47
 */
@Component
@Aspect
public class SetFieldValueAspect {

    @Autowired
    private BeanFieldsUtils beanFieldsUtils;

    @Around("@annotation(com.barry.cloud.platform.oap.annotation.NeedSetField)")
    public Object doSetFieldValue(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret = joinPoint.proceed();

        //设置结果的属性
        if (ret instanceof Collection){
            this.beanFieldsUtils.setFieldValueForCollection((Collection) ret);
        }else {
            //不是集合,也需要设置,BeanFieldsUtils中提供一个设置一个对象的属性的方法接口
            List<Object> list = new ArrayList<>();
            list.add(ret);
            this.beanFieldsUtils.setFieldValueForCollection(list);
        }
        return ret;
    }










}
