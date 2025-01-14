package com.xuecheng.content.mapper;

import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author cliced
 * @Description 针对表【teachplan(课程计划)】的数据库操作Mapper
 * @Date 2025-01-09 21:00:29
 * @Entity com.xuecheng.content.model.po.Teachplan
 */
@Mapper
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
     * 根据课程 id 查询课程计划
     *
     * @param courseId 课程 id
     * @return 课程计划dto
     */
    List<TeachplanDto> selectTreeNodes(Long courseId);
}




