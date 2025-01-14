package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import com.xuecheng.content.mapper.TeachplanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cliced
 * @Description 针对表【teachplan(课程计划)】的数据库操作Service实现
 * @Date 2025-01-14 15:43:21
 */
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
        implements TeachplanService {
    @Autowired
    private TeachplanMapper teachplanMapper;

    /**
     * 根据课程 id 查询课程计划
     *
     * @param courseId 课程 id
     * @return 课程计划
     */
    @Override
    public List<TeachplanDto> queryTreeNodes(Long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        // 排序规则
        Comparator<TeachplanDto> cmp = Comparator.comparingInt(TeachplanDto::getOrderby);
        return queryTreeNodes(0L, teachplanDtos, cmp);
    }

    /**
     * 递归组装课程计划
     *
     * @param id            课程计划 id
     * @param teachplanDtos 课程计划集合
     * @param cmp           排序比较器
     * @return 课程计划集合
     */
    private static List<TeachplanDto> queryTreeNodes(Long id,
                                                     List<TeachplanDto> teachplanDtos,
                                                     Comparator<TeachplanDto> cmp) {
        return teachplanDtos.stream()
                .filter(dto -> dto.getParentid().equals(id))
                .peek(dto -> {
                    if (dto.getGrade() != 2)
                        dto.setTeachPlanTreeNodes(queryTreeNodes(dto.getId(), teachplanDtos, cmp));
                }).sorted(cmp).collect(Collectors.toList());
    }
}
