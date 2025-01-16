package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import com.xuecheng.content.mapper.TeachplanMapper;
import org.springframework.beans.BeanUtils;
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

    /**
     * 根据课程 id 查询课程计划
     *
     * @param courseId 课程 id
     * @return 课程计划
     */
    @Override
    public List<TeachplanDto> queryTreeNodes(Long courseId) {
        List<TeachplanDto> teachplanDtos = getBaseMapper().selectTreeNodes(courseId);
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

    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        // 通过课程计划id判断是新增还是修改
        if (saveTeachplanDto.getId() == null) { // 新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto, teachplan);
            // 确定排序字段: 最大字段+1
            Long courseId = saveTeachplanDto.getCourseId();
            Integer maxOrderby = getMaxOrderby(courseId, 0L);
        }
    }

    private Integer getMaxOrderby(Long courseId, Long parentid) {
        /*
            select * from course_teachplan
            where course_id = ? and parentid = 0
            order by orderby desc limit 1
         */
        QueryWrapper<Teachplan> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("orderby").lambda()
                .eq(Teachplan::getCourseId, courseId)
                .eq(Teachplan::getParentid, parentid)
                .last("limit 1");
        Teachplan teachplan = getBaseMapper().selectOne(queryWrapper);
        return teachplan == null ? 0 : teachplan.getOrderby();
    }
}
