package com.ruoyi.system.service;


import com.ruoyi.system.domain.*;
import org.omg.CORBA.INTERNAL;

import java.awt.geom.Area;
import java.util.List;

public interface TableService {
    /**
     * 添加商家餐桌信息
     *
     * @param
     * @return
     */
    Integer addTable(Integer sid, Integer floorId,Integer areaId,Integer speId, String tableName);

    /**
     * 显示餐桌
     * @param shopId
     * @param floorId
     * @return
     */
    List<Table> selectTable(Integer shopId,Integer floorId,Integer specification,Integer status,Integer area);

    Table selectOrder(Integer shopId,Integer tableId);

    //查询商家的楼层列表
    List<TableFloor> selectFloor(Integer shopId);

    //更新商家餐桌名字
    Integer updateTableName(Integer sid, Integer tableId, String tableName);

    //更新商家楼层名字
    Integer updateFloorName(Integer sid, Integer floorId, String floorName);

    //更新餐桌的状态
    Integer updateTableStatus(Integer sid, Integer tableId, Integer status,Integer population);

    //更新餐桌的状态
    Integer updateTableArea(Integer sid, Integer tableId, Integer area);

    //撤桌
    Integer deleteTable(Integer sid, Integer tableId);

    //加桌
    Integer increaseTable(Integer sid, String tableName);

    //添加楼层餐桌关联信息
    Integer addFloor(Integer shopId, String floorName);

    //减楼层
    Integer deleteFloor(Integer sid, Integer floorId);


    //=============规格=========================

    //添加规格信息
    Integer addSpecification(Integer shopId,String specification);

    //修改规格信息
    Integer updateSpecification(Integer shopId, Integer speId, String specification);

    //查询规格信息
    List<TableSpecification> selectSpecification(Integer shopId);

    //修改规格信息
    Integer deleteSpecification(Integer shopId, Integer speId);

    //==================区域===========================

    //添加商家的区域信息
    Integer addArea(Integer shopId,Integer floorId,String area);

    //修改商家的区域信息
    Integer updateArea(Integer shopId,Integer areaId,String area);

    //查看商家的区域信息
    List<TableArea> selectArea(Integer shopId, Integer floorId);

    //删除商家的区域信息
    Integer deleteArea(Integer shopId,Integer areaId);

    List<Table> selectListTable(Integer shopId);

}
