package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.DishType;
import com.ruoyi.system.domain.Menu;

import com.ruoyi.system.form.MenuForm;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 菜单 数据层
 *
 * @author ruoyi
 */
public interface MenuMapper {

    //查询某一菜品
    Menu findDish(@Param("dishId")Integer dishId);

    //添加菜单
    Integer addMenu(Menu menu);

    //修改菜单
    Integer updateMenu(MenuForm menu);

    //查询所有菜品信息
    List<Menu> findAll(Integer shopId);

    //查看某一商家的某一分类的菜单
    List<Menu> select(@Param("shopId") Integer shopId, @Param("dishType") Integer dishType);

    //查看某一商家的菜单
    List<Menu> selectAll(@Param("shopId") Integer shopId, @Param("typeId") Integer typeId);


    Integer deleteMenu( @Param("id") Integer id);

    //添加菜单分类信息
    Integer addDishType(@Param("shopId") Integer shopId, @Param("dishType") String dishType);

    //查看商家的分类信息
    List<DishType> getDishType(Integer shopId);

    //修改分类信息
    Integer updateDishType(@Param("shopId") Integer shopId, @Param("typeId") Integer typeId, @Param("typeName") String typeName);

    //删除菜单分类
    Integer deleteDishType(@Param("shopId") Integer shopId, @Param("typeId") Integer typeId);

    //删除菜单所有分类
    Integer deleteDishTypeAll(@Param("shopId") Integer shopId);


}
