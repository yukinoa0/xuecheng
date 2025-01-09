package com.xuecheng.content.model.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 课程-教师关系表
 *
 * @TableName course_teacher
 */
@TableName(value = "course_teacher")
@Data
public class CourseTeacher implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 教师标识
     */
    private String teacherName;

    /**
     * 教师职位
     */
    private String position;

    /**
     * 教师简介
     */
    private String introduction;

    /**
     * 照片
     */
    private String photograph;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}