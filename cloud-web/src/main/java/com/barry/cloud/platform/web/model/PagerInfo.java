package com.barry.cloud.platform.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/15 14:48
 */
@Data
@JsonIgnoreProperties
public class PagerInfo<T> implements Serializable {

    private static final long serialVersionUID = 1687520000982742259L;

    @JsonProperty("pager.pageNo")
    private Integer pageNo;

    @JsonProperty("pager.totalRows")
    private Integer totalRows;

    @JsonProperty("rows")
    private List<T> rows;



}
