package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    //菜品管理分页查询
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    //新增菜品数据
    void save(DishDTO dishDTO);

    //删除菜品
    void delete(List<Long> ids);

    //修改界面根据id查询菜品信息
    DishVO get(Long id);

    void update(DishDTO dishDTO);

    //修改状态
    void updateStatus(Integer status, Long id);

    //根据分类id查询菜品
    List<Dish> list(Long categoryId);

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
