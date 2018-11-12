package com.barry.cloud.platform.oap.utils;

import com.barry.cloud.platform.oap.annotation.SetValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description: 获取信息并设值
 * @date 2018/9/18 17:32
 */
@Slf4j
@Component
public class BeanFieldsUtils implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setFieldValueForCollection(Collection collection){
        if (CollectionUtils.isEmpty(collection)){
            log.info("=========================>collection集合中没有要设值的属性");
            return;
        }
        //1. 获取class对象
        Class<?> clazz = collection.iterator().next().getClass();
        //2. 获取class对象里面的字段
        Field[] fields = clazz.getDeclaredFields();
        //3. 遍历处理带有指定注解的字段
        for (Field needSetField : fields){
            //3.1 获取字段的SetValue注解
            SetValue sv = needSetField.getAnnotation(SetValue.class);
            if (sv ==null){
                continue;
            }
            //设置私有字段可以被反射直接访问
            needSetField.setAccessible(true);

            //3.2 在获取注解的信息
            //要调用的bean
            Object bean = this.applicationContext.getBean(sv.beanClass());
            
            try {
                Method method = sv.beanClass().getMethod(sv.method(), clazz.getDeclaredField(sv.paramField()).getType());

                //入参字段
                Field paramField = clazz.getDeclaredField(sv.paramField());
                paramField.setAccessible(true);

                //值来源字段
                Field targetField = null;

                boolean needInnerField = !StringUtils.isEmpty(sv.targetField());

                //4. 遍历对象
                for(Object obj : collection){
                    //4.1 获取参数值
                    Object paramValue = paramField.get(obj);
                    if (paramValue == null){
                        continue;
                    }
                    Object value = null;

                    //4.2 调用bean的方法获得目标对象
                    value = method.invoke(bean, paramValue);

                    if (needInnerField){
                        if (value != null){
                            if (targetField==null){
                                targetField = value.getClass().getDeclaredField(sv.targetField());
                                targetField.setAccessible(true);
                            }
                            value = targetField.get(value);
                        }
                        //4.3 设置值
                        needSetField.set(obj, value);
                    }
                }
            }catch (Exception e){
                log.error("setFieldValueForCollection error {} ", e);
            }

        }
        
    }











}
