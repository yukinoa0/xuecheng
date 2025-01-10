package com.xuecheng.content.service;

import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author cliced
 * @Description 针对表【course_base(课程基本信息)】的数据库操作Service
 * @Date 2025-01-10 11:27:26
 */
public interface CourseBaseService extends IService<CourseBase> {
    /**
     * 课程分页查询
     *
     * @param pageBean       分页参数
     * @param queryCourseDto 查询条件
     * @return 分页结果
     */
    PageResult<CourseBase> queryCourseBaseList(PageBean pageBean, QueryCourseDto queryCourseDto);
}