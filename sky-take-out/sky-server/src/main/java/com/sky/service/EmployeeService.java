package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    //登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    //新增数据
    void insert(EmployeeDTO employeeDTO);

    //分页查询
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    //修改状态
    void updateStatus(Integer status, Long id);

    //根据id查找
    Employee getByid(Long id);

    //修改数据
    void update(EmployeeDTO employeeDTO);
}
