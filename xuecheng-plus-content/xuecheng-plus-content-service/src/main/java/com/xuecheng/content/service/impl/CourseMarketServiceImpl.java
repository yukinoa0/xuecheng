package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseMarketService;
import com.xuecheng.content.mapper.CourseMarketMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author cliced
 * @Description 针对表【course_market(课程营销信息)】的数据库操作Service实现
 * @Date 2025-01-11 18:26:52
 */
@Service
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper, CourseMarket>
        implements CourseMarketService {
    /**
     * 新增课程营销信息
     *
     * @param courseId 课程 id
     * @param dto      新增课程表单信息
     * @return 课程营销信息
     */
    @Override
    public CourseMarket createCourseMarket(Long courseId, AddCourseDto dto) {
        CourseMarket courseMarket = new CourseMarket();
        courseMarket.setId(courseId);
        BeanUtils.copyProperties(dto, courseMarket);
        String charge = courseMarket.getCharge();
        if (StringUtils.isEmpty(charge)) {
            throw new RuntimeException("收费规则为空");
        }
        if (charge.equals("201001")) {
            Double price = courseMarket.getPrice();
            if (price == null || price.floatValue() <= 0) {
                throw new RuntimeException("课程的价格不能为空且必须大于0");
            }
        }
        // 插入或修改数据
        if (!saveOrUpdate(courseMarket)) {
            throw new RuntimeException("新增或修改课程营销信息失败");
        }
        return courseMarket;
    }
}




