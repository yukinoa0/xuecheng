package com.xuecheng.content.model.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 课程基本信息
 *
 * @TableName course_base
 */
@TableName(value = "course_base")
@Data
public class CourseBase implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 机构名称
     */
    private String companyName;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 课程标签
     */
    private String tags;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 小分类
     */
    private String st;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 教育模式(common普通，record 录播，live直播等）
     */
    private String teachmode;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 课程图片
     */
    private String pic;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 更新人
     */
    private String changePeople;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 课程发布状态 未发布  已发布 下线
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}