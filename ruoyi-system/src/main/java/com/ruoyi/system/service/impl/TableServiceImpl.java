package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.TableMapper;
import com.ruoyi.system.service.TableService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.List;

/**
 * 餐桌 业务层处理
 *
 * @author ruoyi
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableMapper tableMapper;


    @Override
    public Integer addTable(Integer sid, Integer floorId, Integer areaId, Integer speId, String tableName) {
        return tableMapper.addTable(sid,floorId,areaId,speId,tableName);
    }

    @Override
    public List<Table> selectTable(Integer shopId, Integer floorId, Integer specification, Integer status,Integer area) {
        return tableMapper.selectTable(shopId,floorId,specification,status,area);
    }

    @Override
    public Table selectOrder(Integer shopId, Integer tableId) {
        return tableMapper.selectOrder(shopId,tableId);
    }


    @Override
    public List<TableFloor> selectFloor(Integer shopId) {
        return tableMapper.selectFloor(shopId);
    }

    @Override
    public Integer updateTableName(Integer sid, Integer tableId, String tableName) {
        return tableMapper.updateTableName(sid,tableId,tableName);
    }

    @Override
    public Integer updateFloorName(Integer sid, Integer floorId, String floorName) {
        return tableMapper.updateFloorName(sid,floorId,floorName);
    }

    @Override
    public Integer updateTableStatus(Integer sid, Integer tableId, Integer status,Integer population) {
        return tableMapper.updateTableStatus(sid, tableId, status,population);
    }

    @Override
    public Integer updateTableArea(Integer sid, Integer tableId, Integer area) {
        return tableMapper.updateTableArea(sid, tableId, area);
    }

    @Override
    public Integer deleteTable(Integer sid, Integer tableId) {
        return tableMapper.deleteTable(sid, tableId);
    }

    @Override
    public Integer increaseTable(Integer sid, String tableName) {
        return tableMapper.increaseTable(sid,tableName);
    }

    @Override
    public Integer addFloor(Integer shopId, String floorName) {
        return tableMapper.addFloor(shopId,floorName);
    }

    @Override
    public Integer deleteFloor(Integer sid, Integer floorId) {
        return tableMapper.deleteFloor(sid,floorId);
    }


    //============================================规格

    @Override
    public Integer addSpecification(Integer shopId, String specification) {
        return tableMapper.addSpecification(shopId,specification);
    }

    @Override
    public Integer updateSpecification(Integer shopId, Integer speId, String specification) {
        return tableMapper.updateSpecification(shopId,speId,specification);
    }

    @Override
    public List<TableSpecification> selectSpecification(Integer shopId) {
        return tableMapper.selectSpecification(shopId);
    }

    @Override
    public Integer deleteSpecification(Integer shopId, Integer speId) {
        return tableMapper.deleteSpecification(shopId,speId);
    }


    //===========================区域


    @Override
    public Integer addArea(Integer shopId, Integer floorId, String area) {
        return tableMapper.addArea(shopId,floorId,area);
    }

    @Override
    public Integer updateArea(Integer shopId, Integer areaId, String area) {
        return tableMapper.updateArea(shopId,areaId,area);
    }

    @Override
    public List<TableArea> selectArea(Integer shopId, Integer floorId) {
        return tableMapper.selectArea(shopId,floorId);
    }

    @Override
    public Integer deleteArea(Integer shopId, Integer areaId) {
        return tableMapper.deleteArea(shopId,areaId);
    }

    @Override
    public List<Table> selectListTable(Integer shopid){
        return tableMapper.selectListTable(shopid);
    }

}
