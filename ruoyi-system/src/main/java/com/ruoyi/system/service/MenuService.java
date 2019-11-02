package com.ruoyi.system.service;


import com.ruoyi.system.domain.DishType;
import com.ruoyi.system.domain.Menu;
import com.ruoyi.system.form.MenuForm;
import org.apache.ibatis.annotations.Param;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

/**
 * 菜单服务层
 */
public interface MenuService {

    //添加菜单信息
    Integer addMenu(Menu menu);

    //修改菜单信息
    Integer updateMenu(MenuForm menu);

    //查询所有菜品信息
    List<Menu> findAll(Integer shopId);

    //查询商家的某一分类的菜品信息
    List<Menu> select(Integer shopId, Integer dishType);

    //查询商家的菜品信息
    List<Menu> selectAll(Integer shopId, Integer typeId);

//删除菜单信息
    Integer deleteMenu(Integer id);

    //添加菜单分类信息
    Integer addDishType(Integer shopId, String dishType);

    //查看菜单分类信息
    List<DishType> getDishType(Integer shopId);

    //修改菜单分类信息
    Integer updateDishType(Integer shopId, Integer typeId, String typeName);

    //删除菜单分类信息
    Integer deleteDishType(Integer shopId, Integer typeId);

    //查询某一菜品
    Menu findDish(Integer dishId);

}
