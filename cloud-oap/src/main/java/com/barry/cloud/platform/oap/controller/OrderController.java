package com.barry.cloud.platform.oap.controller;

import com.barry.cloud.platform.oap.entity.Order;
import com.barry.cloud.platform.oap.service.OrderService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/18 14:24
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Page<Order> query(HttpServletRequest request){
        Integer pageNum = request.getParameter("pageNum") == null ? 1 : Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
        return orderService.findOrders(pageNum, pageSize);
    }









}
