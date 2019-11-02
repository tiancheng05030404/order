package com.ruoyi.system.service.impl;



import com.ruoyi.system.domain.DishType;
import com.ruoyi.system.domain.Menu;
import com.ruoyi.system.form.MenuForm;
import com.ruoyi.system.mapper.MenuMapper;
import com.ruoyi.system.service.MenuService;
import com.ruoyi.system.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    MenuMapper menuMapper;

    @Override
    public Integer addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public Integer updateMenu(MenuForm menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public List<Menu> findAll(Integer shopId) {
        return menuMapper.findAll(shopId);
    }


    @Override
    public List<Menu> select(Integer shopId,Integer dishType) {
        return menuMapper.select(shopId,dishType);
    }

    @Override
    public List<Menu> selectAll(Integer shopId,Integer typeId) {
        return menuMapper.selectAll(shopId,typeId);
    }

    @Override
    public Integer deleteMenu( Integer id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public Integer addDishType(Integer shopId, String dishType) {
        return menuMapper.addDishType(shopId,dishType);
    }

    @Override
    public List<DishType> getDishType(Integer shopId) {
        return menuMapper.getDishType(shopId);
    }

    @Override
    public Integer updateDishType(Integer shopId,Integer typeId,String typeName) {
        return menuMapper.updateDishType(shopId,typeId,typeName);
    }

//后端
    @Override
    public Integer deleteDishType(Integer shopId, Integer typeId) {
        return menuMapper.deleteDishType(shopId,typeId);
    }

    @Override
    public Menu findDish(Integer dishId) {
        return menuMapper.findDish(dishId);
    }


}
