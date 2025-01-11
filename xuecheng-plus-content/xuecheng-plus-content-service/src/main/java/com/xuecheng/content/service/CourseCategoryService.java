package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author cliced
 * @Description 针对表【course_category(课程分类)】的数据库操作Service
 * @Date 2025-01-11 14:51:21
 */
public interface CourseCategoryService extends IService<CourseCategory> {
    /**
     * 根据 id 查询课程分类
     * @param id 课程分类 id
     */
    List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
