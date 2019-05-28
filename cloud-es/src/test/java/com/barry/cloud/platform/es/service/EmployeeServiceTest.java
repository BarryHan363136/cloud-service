package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.es.base.BaseESTest;
import com.barry.cloud.platform.es.dao.EmployeeRepository;
import com.barry.cloud.platform.es.entity.Employee;
import com.barry.cloud.platform.es.id.IdWorker;
import com.barry.cloud.platform.es.utils.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

@Slf4j
public class EmployeeServiceTest extends BaseESTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testQueryEmployeeById() {
        String id = "203785090214396935";
        Employee employee = employeeService.queryEmployeeById(id);
        if (employee != null) {
            log.info("=================>" + JSONMapper.writeObjectAsString(employee));
        }
    }

    @Test
    public void testQueryEmployeesByDepartment() {
        String department = "业务部";
        List<Employee> employeeList = employeeService.findEmployeesByDepartment(department);
        if (employeeList != null && !employeeList.isEmpty()) {
            log.info("=================>" + JSONMapper.writeObjectAsString(employeeList));
        }
    }

    @Test
    public void testBatchAddEmployee() {
        IdWorker idWorker = new IdWorker(1L);
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(idWorker.nextId().toString());
            employee.setName("张三" + i);
            employee.setSex("男");
            employee.setAge(30);
            employee.setMobile("1598635421" + i);
            employee.setAddress("上海市虹口区XXX路" + (i + 1) + "号");
            employee.setDepartment("业务部");
            employee.setPosition("普通员工");
            employee.setBirthDay(new Date());
            employee.setCardNo("61062219750215842" + i);
            employeeList.add(employee);
        }
        employeeService.saveList(employeeList);
    }

    @Test
    public void testSave() {
        IdWorker idWorker = new IdWorker(2L);
        Employee employee = new Employee();
        employee.setId(idWorker.nextId().toString());
        employee.setName("张三" + "001");
        employee.setSex("男");
        employee.setAge(30);
        employee.setMobile("1598635421" + "001");
        employee.setAddress("上海市虹口区XXX路001号");
        employee.setDepartment("业务部");
        employee.setPosition("普通员工");
        employee.setBirthDay(new Date());
        employee.setCardNo("61062219750215842" + "001");
        log.info("=================>" + JSONMapper.writeObjectAsString(employee));
        employeeService.save(employee);
    }

    /**
     * 精确匹配, 可以精确匹配到某个字段值
     */
    @Test
    public void testESTemplate() {
        Pageable pageable = PageRequest.of(0, 50);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(matchPhraseQuery("name", "张三3")).
                withPageable(pageable).
                build();
        List<Employee> list = elasticsearchTemplate.queryForList(searchQuery, Employee.class);
        if (list != null && !list.isEmpty()) {
            log.info("=================>" + JSONMapper.writeObjectAsString(list));
        }
    }

    @Test
    public void testESMultiQuery() {
        // 单个字符串
        //QueryBuilder qb0 = QueryBuilders.termQuery("name", "张三3");
        //QueryBuilder qb = QueryBuilders.boolQuery().must(qb0);

        Pageable pageable = PageRequest.of(0, 50);
        String name = "张三3";
        List<Employee> list = employeeService.findEmployeesByName(name, pageable);
        if (list != null && !list.isEmpty()) {
            log.info("===HHHHHHHHHHHHHH==============>" + JSONMapper.writeObjectAsString(list));
        }
    }

    /**
     * 精确匹配
     */
    @Test
    public void testExactQuery() {
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.termQuery("name", "张三3"));
        Page<Employee> page = employeeRepository.search(qb, PageRequest.of(1, 50));
        if (page != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(page));
        }
    }
    @Test
    public void testExactQuery2() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("name","张三3"));

        //查询到的结果，自动分页，默认第一页，每页条目数是10条（itemRepository相当于文档读取器，参数中的文档查询对象需要构建下）
        //在查询条件生成器中生成查询对象所以去build构建
        Page<Employee> search = employeeRepository.search(nativeSearchQueryBuilder.build());
        if (search != null) {
            log.info("===testExactQuery==============>" + JSONMapper.writeObjectAsString(search));
        }
    }





}
