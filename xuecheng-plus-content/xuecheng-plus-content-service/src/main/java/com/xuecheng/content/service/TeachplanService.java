package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author cliced
 * @Description 针对表【teachplan(课程计划)】的数据库操作Service
 * @Date 2025-01-14 15:43:21
 */
public interface TeachplanService extends IService<Teachplan> {
    /**
     * 根据课程 id 查询课程计划
     *
     * @param courseId 课程 id
     * @return 课程计划
     */
    List<TeachplanDto> queryTreeNodes(Long courseId);

    /**
     * 创建或修改课程计划
     * @param saveTeachplanDto 保存课程计划dto
     */
    void saveTeachplan(SaveTeachplanDto saveTeachplanDto);
}
