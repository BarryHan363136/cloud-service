package com.barry.cloud.platform.oap.service.imp;

import com.barry.cloud.platform.oap.entity.Customer;
import com.barry.cloud.platform.oap.mapper.CustomerMapper;
import com.barry.cloud.platform.oap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/18 17:04
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findById(Long id) {
        return customerMapper.find(id);
    }
}
