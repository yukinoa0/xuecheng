package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.CustomException;
import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.CourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseService;
import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.content.service.CourseMarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author cliced
 * @Description 针对表【course_base(课程基本信息)】的数据库操作ServiceImpl
 * @Date 2025/01/10 11:28
 */
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
        implements CourseBaseService {
    @Autowired
    private CourseCategoryService courseCategoryService;

    @Autowired
    private CourseMarketService courseMarketService;

    /**
     * 课程分页查询
     *
     * @param pageBean       分页参数
     * @param queryCourseDto 查询条件
     * @return 分页结果
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageBean pageBean, QueryCourseDto queryCourseDto) {
        Long pageNo = pageBean.getPageNo();
        Long pageSize = pageBean.getPageSize();
        String courseName = queryCourseDto.getCourseName();
        String auditStatus = queryCourseDto.getAuditStatus();
        String publishStatus = queryCourseDto.getPublishStatus();
        // 拼装查询条件，并分页查询
        // where course_base.name like '?%' and course_base.audit_status = ? and course_base.status = ?
        Page<CourseBase> queryPage = lambdaQuery()
                .like(StringUtils.isNotEmpty(courseName), CourseBase::getName, courseName)
                .eq(StringUtils.isNotEmpty(auditStatus), CourseBase::getAuditStatus, auditStatus)
                .eq(StringUtils.isNotEmpty(publishStatus), CourseBase::getStatus, publishStatus)
                .page(Page.of(pageNo, pageSize));
        return new PageResult<>(queryPage.getRecords(), queryPage.getTotal(), pageNo, pageSize);
    }

    /**
     * 新增课程
     *
     * @param companyId 机构 id
     * @param courseDto 新增课程表单信息
     * @return 课程详细信息
     */
    @Transactional
    @Override
    public CourseBaseInfoDto createCourse(Long companyId, CourseDto courseDto) {
        // 向课程基本信息表 course_base 添加数据
        CourseBase courseBase = createCourseBase(companyId, courseDto);
        // 向课程营销表 course_market 添加数据
        CourseMarket courseMarket = courseMarketService.createCourseMarket(courseBase.getId(), courseDto);
        // 返回课程详细信息
        return getCourseBaseInfo(courseBase, courseMarket);
    }

    /**
     * 新增课程基本信息
     *
     * @param companyId 用户所属机构 id
     * @param courseDto 新增课程表单信息
     * @return 课程基本信息
     */
    @Override
    public CourseBase createCourseBase(Long companyId, CourseDto courseDto) {
        CourseBase courseBase = new CourseBase();
        // 属性拷贝
        BeanUtils.copyProperties(courseDto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002"); // 审核状态默认为未提交
        courseBase.setStatus("203001"); // 发布状态默认为未发布
        // 插入数据
        if (!save(courseBase)) {
            CustomException.cast("新增课程失败");
        }
        return courseBase;
    }

    /**
     * 根据课程 id 查询基本信息
     *
     * @param courseId 课程 id
     * @return 课程基本信息
     */
    @Override
    public CourseBase queryCourseBaseById(Long courseId) {
        return lambdaQuery().eq(CourseBase::getId, courseId).one();
    }

    /**
     * 根据课程 id 查询课程信息
     *
     * @param courseId 课程 id
     * @return 课程详细信息
     */
    @Override
    public CourseBaseInfoDto queryCourseById(Long courseId) {
        // 查询课程基本信息
        CourseBase courseBase = queryCourseBaseById(courseId);
        // 查询课程营销信息
        CourseMarket courseMarket = courseMarketService.queryCourseMarketById(courseId);
        // 返回课程详细信息
        return getCourseBaseInfo(courseBase, courseMarket);
    }

    /**
     * 修改课程信息
     *
     * @param companyId 机构 id
     * @param courseDto 修改的课程信息
     * @return 课程详细信息
     */
    @Transactional
    @Override
    public CourseBaseInfoDto updateCourse(Long companyId, CourseDto courseDto) {
        // 参数合法性校验
        // 查询课程是否存在
        CourseBase courseBase = queryCourseBaseById(courseDto.getId());
        if (courseBase == null) {
            CustomException.cast("课程不存在");
        }
        // 用户所属机构与课程所属机构的 id 是否一致
        if (!courseBase.getCompanyId().equals(companyId)) {
            CustomException.cast("只能修改本机构的课程");
        }
        // 更新课程基本信息
        BeanUtils.copyProperties(courseDto, courseBase); // 属性拷贝
        courseBase.setChangeDate(LocalDateTime.now()); // 设置基本时间
        if (!updateById(courseBase)) {
            CustomException.cast("修改课程失败");
        }
        // 更新课程营销信息
        CourseMarket courseMarket = courseMarketService.updateCourseMarket(courseDto);
        return getCourseBaseInfo(courseBase, courseMarket);
    }

    /**
     * 根据课程基本信息和营销信息，返回详细信息
     *
     * @param courseBase   课程基本信息
     * @param courseMarket 课程营销信息
     * @return 课程详细信息
     */
    private CourseBaseInfoDto getCourseBaseInfo(CourseBase courseBase, CourseMarket courseMarket) {
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        // 属性拷贝
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        // 查询课程分类: select * from course_category where id in (st, mt)
        String st = courseBase.getSt();
        String mt = courseBase.getMt();
        Map<String, String> categoryMap = courseCategoryService.lambdaQuery()
                .in(CourseCategory::getId, st, mt).list().stream()
                .collect(Collectors.toMap(CourseCategory::getId, CourseCategory::getName));
        courseBaseInfoDto.setStName(categoryMap.get(st));
        courseBaseInfoDto.setMtName(categoryMap.get(mt));
        return courseBaseInfoDto;
    }
}
