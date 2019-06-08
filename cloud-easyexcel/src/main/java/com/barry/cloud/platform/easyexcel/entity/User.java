package com.barry.cloud.platform.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Author:
 * @email:
 * @Description: 用户实体类
 * @Date: 2019/5/13/0013 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = -6937412768185068015L;

    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    @ExcelProperty(value = "密码",index = 1)
    private String password;

    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;

    @ExcelProperty(index = 2,format = "yyyy-MM-dd HH:mm:ss")
    private Date birthTime;


}