package com.ruoyi.system.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuForm {

    private Integer id;

    private String dishName;

    private Double dishPrice;

    private MultipartFile dishImage;

    private Integer dishType;

    private Integer shopId;
}
