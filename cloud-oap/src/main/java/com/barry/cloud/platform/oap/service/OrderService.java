package com.barry.cloud.platform.oap.service;

import com.barry.cloud.platform.oap.entity.Order;
import com.github.pagehelper.Page;

public interface OrderService {

    public Page<Order> findOrders(Integer pageNum, Integer pageSize);

}
