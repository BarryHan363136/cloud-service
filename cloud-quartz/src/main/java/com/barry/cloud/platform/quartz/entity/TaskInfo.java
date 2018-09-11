package com.barry.cloud.platform.quartz.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "spark_task_info")
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = -8808768206866161954L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "job_group", nullable = false)
    private String jobGroup;

    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "job_status", nullable = false)
    private Integer jobStatus;

    @Column(name = "bean_name")
    private String beanName;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "remark")
    private String remark;




}