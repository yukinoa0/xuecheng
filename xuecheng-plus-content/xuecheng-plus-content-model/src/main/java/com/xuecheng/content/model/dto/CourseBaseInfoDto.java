package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Mr.M
 * @version 1.0
 * @Description 课程基本信息dto
 * @Date 2022/9/7 17:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseBaseInfoDto extends CourseBase {
    /**
     * 收费规则，对应数据字典
     */
    private String charge;

    /**
     * 价格
     */
    private Double price;

    /**
     * 原价
     */
    private Double originalPrice;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 有效期天数
     */
    private Integer validDays;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类名称
     */
    private String stName;
}