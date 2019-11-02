package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class Table {
    Integer id;
    Integer sid;
    String tableName;
    Integer status;
    Integer floor;
    String floorName;
    Integer specification;
    String specificationName;
    Double price;
    Integer area;
    String areaName;
    Integer population;
}
