package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    //套餐分页查询
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    //新增套餐
    void insert(SetmealDTO setmealDTO);

    //删除套餐
    void delete(List<Long> ids) throws Exception;

    //根据id查询套餐
    SetmealVO get(Long id);

    //修改套餐
    void update(SetmealDTO setmealDTO);

    //修改状态
    void updateStatus(Integer status, Long id);

    /**
     * 条件查询
     *
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     *
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
