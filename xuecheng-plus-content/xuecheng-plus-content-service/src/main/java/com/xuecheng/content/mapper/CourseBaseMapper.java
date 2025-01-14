package com.xuecheng.content.mapper;

import com.xuecheng.content.model.po.CourseBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author cliced
 * @Description 针对表【course_base(课程基本信息)】的数据库操作Mapper
 * @Date 2025-01-09 21:00:29
 * @Entity com.xuecheng.content.model.po.CourseBase
 */
@Mapper
public interface CourseBaseMapper extends BaseMapper<CourseBase> {

}




