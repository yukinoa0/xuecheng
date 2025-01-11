package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
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
                .page(new Page<>(pageNo, pageSize));
        return new PageResult<>(queryPage.getRecords(), queryPage.getTotal(), pageNo, pageSize);
    }

    /**
     * 新增课程
     *
     * @param companyId 机构 id
     * @param dto       新增课程表单信息
     * @return 课程详细信息
     */
    @Transactional
    @Override
    public CourseBaseInfoDto createCourse(Long companyId, AddCourseDto dto) {
        // 参数合法性校验
        checkAddCourseDto(dto);
        // 向课程基本信息表 course_base 添加数据
        CourseBase courseBase = createCourseBase(companyId, dto);
        // 向课程营销表 course_market 添加数据
        CourseMarket courseMarket = courseMarketService.createCourseMarket(courseBase.getId(), dto);
        // 返回课程详细信息
        return getCourseBaseInfo(courseBase, courseMarket);
    }

    /**
     * 新增课程基本信息
     *
     * @param companyId 用户所属机构 id
     * @param dto       新增课程表单信息
     * @return 课程基本信息
     */
    @Override
    public CourseBase createCourseBase(Long companyId, AddCourseDto dto) {
        CourseBase courseBase = new CourseBase();
        // 属性拷贝
        BeanUtils.copyProperties(dto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002"); // 审核状态默认为未提交
        courseBase.setStatus("203001"); // 发布状态默认为未发布
        // 插入数据
        if (!save(courseBase)) {
            throw new RuntimeException("新增课程失败");
        }
        return courseBase;
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
        // 查询课程发布状态
        String stName = courseCategoryService.lambdaQuery()
                .eq(CourseCategory::getId, courseBase.getSt())
                .one().getName();
        String mtName = courseCategoryService.lambdaQuery()
                .eq(CourseCategory::getId, courseBase.getMt())
                .one().getName();
        courseBaseInfoDto.setStName(stName);
        courseBaseInfoDto.setMtName(mtName);
        return courseBaseInfoDto;
    }

    public boolean checkAddCourseDto(AddCourseDto dto) {
        //合法性校验
        if (StringUtils.isBlank(dto.getName())) {
            throw new RuntimeException("课程名称为空");
        }
        if (StringUtils.isBlank(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }
        if (StringUtils.isBlank(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }
        if (StringUtils.isBlank(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }
        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }
        if (StringUtils.isBlank(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }
        if (StringUtils.isBlank(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }
        return true;
    }
}
