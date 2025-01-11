package com.xuecheng.content.mapper;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @Author cliced
* @Description 针对表【course_category(课程分类)】的数据库操作Mapper
* @Date 2025-01-09 21:00:29
* @Entity com.xuecheng.content.model.po.CourseCategory
*/
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    /**
     * 递归查询课程分类
     * @param id 课程分类 id
     */
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}




