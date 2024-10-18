package com.xuecheng.content.model.dto;

import lombok.Data;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程查询条件模型类
 * @Date 2024/10/18 19:41
 */
@Data
public class QueryCourseDto {
    /**
     * 课程审核状态
     */
    private String auditStatus;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程发布状态
     */
    private String publishStatus;

    public QueryCourseDto() {
    }

    public QueryCourseDto(String auditStatus, String courseName, String publishStatus) {
        this.auditStatus = auditStatus;
        this.courseName = courseName;
        this.publishStatus = publishStatus;
    }
}
