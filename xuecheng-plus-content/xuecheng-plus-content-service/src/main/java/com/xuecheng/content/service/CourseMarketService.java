package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseDto;
import com.xuecheng.content.model.po.CourseMarket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author cliced
 * @Description 针对表【course_market(课程营销信息)】的数据库操作Service
 * @Date 2025-01-11 18:26:52
 */
public interface CourseMarketService extends IService<CourseMarket> {
    /**
     * 新增课程营销信息
     *
     * @param courseId  课程 id
     * @param courseDto 新增课程表单信息
     * @return 课程营销信息
     */
    CourseMarket createCourseMarket(Long courseId, CourseDto courseDto);
}
