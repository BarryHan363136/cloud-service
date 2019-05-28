package com.barry.cloud.platform.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "spark_data_staff",type = "employee")
public class Employee implements Serializable {

    @Id
    private String id;

    //@Field
    private String name;

    //@Field
    private String sex;

    //@Field
    private Integer age;

    //@Field
    private String mobile;

    //@Field
    private String address;

    //@Field
    private String department;

    //@Field
    private String position;

    //@Field
    private Date birthDay;

    //@Field
    private String cardNo;

    public Employee(){

    }

    public Employee(String id){
        this.id = id;
    }

}
