package com.barry.cloud.platform.oap.controller;

import com.barry.cloud.platform.oap.entity.Customer;
import com.barry.cloud.platform.oap.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/18 14:24
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find/{id}")
    public Customer query(@PathVariable("id") Long id){
        return customerService.findById(id);
    }









}
