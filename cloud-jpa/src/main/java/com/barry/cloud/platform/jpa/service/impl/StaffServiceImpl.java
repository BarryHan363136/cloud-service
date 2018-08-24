package com.barry.cloud.platform.jpa.service.impl;

import com.barry.cloud.platform.jpa.dao.StaffRepository;
import com.barry.cloud.platform.jpa.entity.Staff;
import com.barry.cloud.platform.jpa.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:29
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Page<Staff> findResult(Staff staff, Integer pageNumber, Integer pageSize) {
        if(pageNumber==null) pageNumber=1;
        if(pageSize==null) pageSize=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of((pageNumber-1), pageSize, sort);
        Page<Staff> staffPage = staffRepository.findAll(new Specification<Staff>() {
            @Override
            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /**
                 * 连接查询条件, 不定参数，可以连接0..N个查询条件
                 * 查询条件比对方式:
                 * 1. criteriaBuilder.equal
                 * 2. criteriaBuilder.between
                 * 3. criteriaBuilder.lessThan
                 * 4. criteriaBuilder.greaterThan
                 *
                 */
                List<Predicate> predicateList = new ArrayList<>();
                if (staff.getId()!=null){
                    Path<String> dbTid = root.get("id");
                    predicateList.add(criteriaBuilder.equal(dbTid, staff.getId()));
                }
                if (StringUtils.isNotBlank(staff.getUserName())){
                    Path<String> userName = root.get("userName");
                    predicateList.add(criteriaBuilder.equal(userName, staff.getUserName()));
                }
                if (StringUtils.isNotBlank(staff.getRealName())){
                    Path<String> realName = root.get("realName");
                    predicateList.add(criteriaBuilder.equal(realName, staff.getRealName()));
                }
                return null;
            }
        }, pageable);
        return staffPage;
    }

    @Override
    public Staff save(Staff user) {
        return staffRepository.save(user);
    }

    @Override
    public Page<Staff> findAll(Staff staff, Integer pageNumber, Integer pageSize) {
        /** 创建匹配器，即如何使用查询条件 */
        ExampleMatcher matcher = ExampleMatcher.matching();
        if (staff.getId()!=null){
            matcher.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (StringUtils.isNotBlank(staff.getUserName())){
            matcher.withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (StringUtils.isNotBlank(staff.getRealName())){
            matcher.withMatcher("realName", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.valueOf(staff.getRealName())));
        }
        /**
         * 忽略属性：是否关注。因为是基本类型，需要忽略掉
         * .withIgnorePaths("id")
         */
        /** 创建实例 */
        Example<Staff> example = Example.of(staff, matcher);
        /**
         * 分页
         * Pageable是接口，PageRequest是接口实现
         * PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
         * 以前是用new PageRequest(pageNo,pageSize,Sort.Direction.DESC,"id")的方法，但是那个2。7之后就被遗弃了，现在直接用PageRequest.of的方法。简单粗暴，无需new。
         * 并且这里最好做空值判断，不然没传值就容易空指针异常
         * */
        if(pageNumber==null) pageNumber=1;
        if(pageSize==null) pageSize=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);

        /** 查询 */
        Page<Staff> staffPage = staffRepository.findAll(example, pageable);

        return staffPage;
    }

    @Override
    public Staff findOne(String userName) {
        List<Staff> list = staffRepository.findByUserName(userName);
        if (list!=null && !list.isEmpty()){
            Staff staff = list.get(0);
            return staff;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Staff staff = new Staff();
        staff.setId(id);
        staffRepository.delete(staff);
        return true;
    }



}
