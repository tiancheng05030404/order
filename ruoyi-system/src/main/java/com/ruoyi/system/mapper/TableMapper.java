package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.*;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.awt.geom.Area;
import java.util.List;

/**
 * 餐桌 数据层
 *
 * @author ruoyi
 */
public interface TableMapper {
    Integer addTable(@Param("shopId") Integer sid,@Param("floorId")Integer floorId,@Param("areaId")Integer areaId,@Param("speId")Integer speId, @Param("tableName")String tableName);


    //查询餐桌
    List<Table> selectTable(@Param("shopId") Integer shopId, @Param("floorId") Integer floorId, @Param("speId") Integer speId, @Param("status") Integer status, @Param("area") Integer area);


    //查询餐桌订单表
    Table selectOrder(@Param("shopId") Integer shopId, @Param("tableId") Integer tableId);


    List<TableFloor> selectFloor(Integer shopId);

    Integer updateTableName(@Param("shopId") Integer shopId, @Param("tableId") Integer tableId, @Param("tableName") String tableName);

    Integer updateFloorName(@Param("shopId") Integer shopId, @Param("floorId") Integer floorId, @Param("floorName") String floorName);

    Integer updateTableStatus(@Param("shopId") Integer shopId, @Param("tableId") Integer tableId, @Param("status") Integer status,@Param("population")Integer population);

    Integer updateTableArea(@Param("shopId") Integer shopId, @Param("tableId") Integer tableId, @Param("area") Integer area);

    //撤桌
    Integer deleteTable(@Param("shopId") Integer shopId, @Param("tableId") Integer tableId);

    //减楼层
    Integer deleteFloor(@Param("shopId") Integer shopId, @Param("floorId") Integer floorId);

    //加桌
    Integer increaseTable(@Param("shopId") Integer sid, @Param("tableName") String tableName);

    //查看某一商家的最大默认餐桌号
    Integer getMaxId(Integer shopId);

    //查看某一商家的最大默认楼层号
    Integer getMaxFloorId(Integer shopId);

    //通过楼层号删除餐桌
    Integer deleteByFloor(@Param("floorId") Integer floor,@Param("shopId") Integer shopId);

    //添加楼层餐桌关联信息
    Integer addFloor(@Param("shopId") Integer shopId, @Param("floorName") String floorName);

//========================================规格


    //添加规格信息
    Integer addSpecification(@Param("shopId") Integer shopId, @Param("specification") String specification);

    //修改规格信息
    Integer updateSpecification(@Param("shopId") Integer shopId, @Param("speId") Integer speId, @Param("specification") String specification);

    //查询规格信息
    List<TableSpecification> selectSpecification(@Param("shopId") Integer shopId);

    //删除规格信息
    Integer deleteSpecification(@Param("shopId") Integer shopId, @Param("speId") Integer speId);

    //通过规格删除餐桌
    Integer deleteBySpe(@Param("speId") Integer speId,@Param("shopId") Integer shopId);

    //通过区域删除餐桌
    Integer deleteByArea(@Param("areaId") Integer areaId,@Param("shopId") Integer shopId);

//===========================

    List<TableStatus> selectStatus();

    List<Table> select(Integer shopId);


//==================区域

    //添加商家的区域信息
    Integer addArea(@Param("shopId") Integer shopId,@Param("floorId") Integer floorId,@Param("area") String area);

    //修改商家的区域名字信息
    Integer updateArea(@Param("shopId")Integer shopId,@Param("areaId") Integer areaId,@Param("area")String area);

    //查看商家的区域信息
    List<TableArea> selectArea(@Param("shopId")Integer shopId,@Param("floorId") Integer floorId);

    //删除商家的区域信息
    Integer deleteArea(@Param("shopId")Integer shopId,@Param("areaId")Integer areaId);

    //查询店铺餐桌信息
    List<Table> selectListTable(Integer shopId);

}
