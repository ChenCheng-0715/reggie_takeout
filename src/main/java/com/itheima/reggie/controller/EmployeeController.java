package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: EmployeeController
 * Package: com.itheima.reggie.controller
 * Description:
 *
 * @Author:
 * @Create: 5/10/2023 - 8:38 pm
 * @Version:
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * employee login
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,  @RequestBody Employee employee) {


        // 1 encrypt password md5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2 query db by username
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3 if no such user, return
        if (emp == null) {
            return R.error("Login Fail");
        }

        // 4 if password not match, return
        if (!emp.getPassword().equals(password)) {
            return R.error("Login Fail");
        }

        // 5 if employee disabled, return
        if (emp.getStatus() == 0) {
            return R.error("User disabled");
        }

        // 6 login success, save employee id into session and return success
        request.getSession().setAttribute("employee", emp.getId());

        return R.success(emp);
    }
}
