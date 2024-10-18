package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 分页查询参数
 * @Date 2024/10/18 19:24
 */
@Data
@ToString
public class PageBean {
    /**
     * 当前页码
     */
    private Long pageNo = 1L;

    /**
     * 每页记录数，默认 10
     */
    private Long pageSize = 10L;

    public PageBean() {
    }

    public PageBean(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
