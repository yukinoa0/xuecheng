package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.CustomException;
import com.xuecheng.content.model.dto.CourseDto;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseMarketService;
import com.xuecheng.content.mapper.CourseMarketMapper;
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
     * @param courseId  课程 id
     * @param courseDto 课程表单信息
     * @return 课程营销信息
     */
    @Override
    public CourseMarket createCourseMarket(Long courseId, CourseDto courseDto) {
        CourseMarket courseMarket = new CourseMarket();
        courseMarket.setId(courseId);
        return saveOrUpdateCourseMarket(courseDto, courseMarket);
    }

    /**
     * 根据课程 id 查询营销信息
     *
     * @param courseId 课程 id
     * @return 课程营销信息
     */
    @Override
    public CourseMarket queryCourseMarketById(Long courseId) {
        return lambdaQuery().eq(CourseMarket::getId, courseId).one();
    }

    /**
     * 修改课程营销信息
     *
     * @param courseDto 课程信息
     * @return 课程营销信息
     */
    @Override
    public CourseMarket updateCourseMarket(CourseDto courseDto) {
        return saveOrUpdateCourseMarket(courseDto, new CourseMarket());
    }

    /**
     * 新增或修改课程营销信息
     *
     * @param courseDto    课程信息
     * @param courseMarket 课程营销信息
     * @return 课程营销信息
     */
    private CourseMarket saveOrUpdateCourseMarket(CourseDto courseDto, CourseMarket courseMarket) {
        BeanUtils.copyProperties(courseDto, courseMarket); // 属性拷贝
        String charge = courseMarket.getCharge();
        if ("201001".equals(charge)) {
            Double price = courseMarket.getPrice();
            if (price == null || price <= 0) {
                CustomException.cast("课程价格不能为空且必须大于0");
            }
        }
        // 插入或修改数据
        if (!saveOrUpdate(courseMarket)) {
            CustomException.cast("新增或修改课程营销信息失败");
        }
        return courseMarket;
    }
}




