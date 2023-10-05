package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: EmployeeMapper
 * Package: com.itheima.reggie.mapper
 * Description:
 *
 * @Author:
 * @Create: 5/10/2023 - 8:36 pm
 * @Version:
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
