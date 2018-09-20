package com.barry.cloud.platform.oap.entity;

import com.barry.cloud.platform.oap.annotation.SetValue;
import com.barry.cloud.platform.oap.mapper.CustomerMapper;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 10:43
 */
@Data
@Entity
@Table(name = "spark_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -3799811038594229996L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    /**
     * @Transient 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性；
     * 如果一个属性并非数据库表的字段映射，就务必将其标示为@Transient，否则ORM框架默认其注解为@Basic；
     * 表示该字段在数据库表中没有
     * */
    @Transient
    @SetValue(beanClass = CustomerMapper.class, method = "find", paramField = "customerId", targetField = "name")
    private String customerName;

}