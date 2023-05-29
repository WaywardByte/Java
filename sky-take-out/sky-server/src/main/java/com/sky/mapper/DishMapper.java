package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    //菜品管理分页查询
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);


    //新增菜品
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    //根据主键查询菜品
    @Select("select * from dish where id = #{id}")
    Dish getByid(Long id);

    //删除
    @Delete("delete from dish where id = #{id}")
    void delete(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    List<Dish> list(Dish dish);

    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getByStatusId(Long id);

    Integer countByMap(Map map);
}
