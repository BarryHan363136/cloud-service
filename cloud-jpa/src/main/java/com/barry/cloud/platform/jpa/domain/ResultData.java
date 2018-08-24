package com.barry.cloud.platform.jpa.domain;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/24 18:09
 */
@Data
public class ResultData implements Serializable {

    private static final long serialVersionUID = -7244820320914813284L;

    /**
     * SUCCESS(200),//成功
     * FAIL(400),//失败
     * UNAUTHORIZED(401),//未认证（签名错误）
     * NOT_FOUND(404),//接口不存在
     * INTERNAL_SERVER_ERROR(500);//服务器内部错误
     * */
    private Integer code;
    private String message;
    private Object returnObject;
    private Integer pageNumber;
    private Integer pageSize;
    private String totalRows;
    private String totalPages;

}
