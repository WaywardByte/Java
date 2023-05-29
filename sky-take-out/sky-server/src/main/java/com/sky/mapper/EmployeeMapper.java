package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //根据姓名查找
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    //新增数据
    @AutoFill(OperationType.INSERT)
    void insert(Employee employee);

    //分页查询
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    //修改状态和数据
    @AutoFill(OperationType.UPDATE)
    void updateStatus(Employee employee);

    //根据id查询
    @Select("select * from employee where id = #{id}")
    Employee getByid(Long id);
}
