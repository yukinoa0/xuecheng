package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author cliced
 * @Description 针对表【course_category(课程分类)】的数据库操作Service实现
 * @Date 2025-01-11 14:51:21
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
        implements CourseCategoryService {
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 根据id查询课程分类
     *
     * @param id 课程分类id
     */
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        List<CourseCategoryTreeDto> treeDtos = courseCategoryMapper.selectTreeNodes(id);
        return queryTreeNodes(id, treeDtos);
    }

    /**
     * 根据 id 递归查询并添加课程分类的子树节点
     *
     * @param id       课程分类 id
     * @param treeDtos 待查询的课程分类集合
     */
    private List<CourseCategoryTreeDto> queryTreeNodes(String id,
                                                       List<CourseCategoryTreeDto> treeDtos) {
        return treeDtos.stream()
                .filter(dto -> Objects.equals(dto.getParentid(), id))
                .peek(dto -> {
                    if (dto.getIsLeaf() != 1) // 如果 dto 不是叶子节点，查询 dto 的子树节点
                        dto.setChildrenTreeNodes(queryTreeNodes(dto.getId(), treeDtos));
                }).collect(Collectors.toList());
    }
}




