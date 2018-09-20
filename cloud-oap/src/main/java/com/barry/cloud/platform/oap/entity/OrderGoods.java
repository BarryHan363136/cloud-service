package com.barry.cloud.platform.oap.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 10:43
 */
@Data
@Entity
@Table(name = "spark_order_goods")
public class OrderGoods {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "goods_id", nullable = false)
    private Long goodsId;

    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

}