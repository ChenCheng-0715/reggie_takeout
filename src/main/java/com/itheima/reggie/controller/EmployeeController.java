package com.itheima.reggie.controller;

import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
