package com.ruoyi.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.AliOssUtil;
import com.ruoyi.common.base.Result;
import com.ruoyi.common.base.StringToObject;
import com.ruoyi.system.domain.DishType;
import com.ruoyi.system.domain.Menu;
import com.ruoyi.system.form.MenuForm;
import com.ruoyi.system.mapper.MenuMapper;
import com.ruoyi.system.service.MenuService;
import com.ruoyi.system.service.ShopService;
import com.ruoyi.system.service.TableService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {


    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessId}")
    private String accessId;

    @Value("${oss.accessKey}")
    private String accessKey;

    @Value("${oss.bucket}")
    private String bucket;

//    private String folder = "uploadfiles/";

    @Autowired
    MenuService menuService;
    @Autowired
    private ShopService shopService;

    @Autowired
    private TableService tableService;

    @Autowired
    MenuMapper menuMapper;


    /**
     * 后端接口
     * 通过shopId查看菜单
     * @param shopId  店铺id
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("shopId")Integer shopId,
                             Map<String,Object> map){
        List<Menu> menuList=menuService.findAll(shopId);
        List<DishType> dishTypeList=menuService.getDishType(shopId);
        map.put("dishTypeList",dishTypeList);
        map.put("shopId",shopId);
        map.put("menus",menuList);
        return new ModelAndView("/food/list",map);
    }


    /**
     * 后端接口
     * 通过shopid typeID查询菜单
     * @param shopId 店铺id
     * @param typeId 类目id
     * @param map    类目对象
     * @return
     */
    @GetMapping("/type")
    public ModelAndView type(@RequestParam("shopId")Integer shopId,
                             @RequestParam("typeId")Integer typeId,
                             Map<String, Object> map){
        List<Menu> menuList=menuService.select(shopId,typeId);
        List<DishType> dishTypeList=menuService.getDishType(shopId);
        map.put("dishTypeList",dishTypeList);
        map.put("shopId",shopId);
        map.put("menus",menuList);
        return new ModelAndView("/food/list",map);
    }

    /**
     * 后端接口
     * 根据店铺id获得店铺菜品分类列表
     * @param shopId 店铺id
     * @param map   类目对象
     * @return
     */
    @GetMapping("/findDishType")
    public ModelAndView menuy(@RequestParam("shopId")Integer shopId,
                              Map<String,Object> map){
       List<DishType> TypeList= menuService.getDishType(shopId);
        map.put("shopId",shopId);
        map.put("TypeList",TypeList);
        return new ModelAndView("/food/addMenu",map);
    }

    /**
     * 后端接口
     * 跳转添加菜品
     * @param map
     * @return
     */

    @GetMapping("/goAdd")
    public ModelAndView goAdd(@RequestParam("shopId")Integer shopId,
                              Map<String, Object>map){
        map.put("shopId" , shopId);
        return new ModelAndView("food/addMenu",map);
    }

    /**
     * 后端接口
     * 添加菜品
     * @param menu
     * @param map
     * @return
     */
    @PostMapping("/addMenu" )
    public ModelAndView addMenu(@Valid MenuForm menu,
                                Map<String,Object>map) throws IOException {


        InputStream inputStream = menu.getDishImage().getInputStream();
        String[] suffix = menu.getDishImage().getOriginalFilename().split("\\.");
        int suffixIndex = suffix.length - 1;
        String ossPath = AliOssUtil.uploadFile(inputStream, bucket+"/"+menu.getShopId(), endpoint, accessId, accessKey,   new Date().getTime() + "." + suffix[suffixIndex]);
        System.out.println("````````````````" + ossPath);

        Menu m=new Menu();
        m.setShopId(menu.getShopId());
        m.setDishName(menu.getDishName());
        m.setDishPrice(menu.getDishPrice());
        m.setDishImage(ossPath);
        m.setTypeId(menu.getDishType());

        menuService.addMenu(m);
        List<Menu> menuList=menuService.findAll(menu.getShopId());
        List<DishType> dishTypeList=menuService.getDishType(menu.getShopId());
        map.put("dishTypeList",dishTypeList);
        map.put("shopId",menu.getShopId());
        map.put("menus",menuList);
       return new ModelAndView("/food/list",map);
    }

    /**
     * 后端接口
     * 跳转修改菜品
     * @return
     */

    @GetMapping("/goUpdate")
    public ModelAndView goUpdate(@RequestParam("dishId")Integer dishId,
                                 @RequestParam("shopId") Integer shopId,
                                 Map<String, Object> map){
        Menu menu=menuService.findDish(dishId);
        List<DishType> dishTypeList=menuService.getDishType(shopId);
        map.put("TypeList",dishTypeList);
        map.put("shopId" , shopId);
        map.put("menu" ,menu);
        return new ModelAndView("/food/index");
    }
    /**
     * 后端接口
     * 修改菜品
     * @return
     */

    @PostMapping("/updateMenu")
    public ModelAndView updateMenu(@Valid MenuForm menu,
                                   Map<String, Object> map){
        menuService.updateMenu(menu);
        List<Menu> menuList=menuService.findAll(menu.getShopId());
        List<DishType> dishTypeList=menuService.getDishType(menu.getShopId());
        Integer shopId=menu.getShopId();
        map.put("dishTypeList",dishTypeList);
        map.put("shopId",menu.getShopId());
        map.put("menus",menuList);
        return new ModelAndView("/food/list",map);
    }

    /**
     * 后端接口
     * 删除菜品
     * @return
     */
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("dishId")Integer dishId,
                               @RequestParam("shopId") Integer shopId,
                               Map<String, Object> map){
        menuService.deleteMenu(dishId);
        List<Menu> menuList=menuService.findAll(shopId);
        List<DishType> dishTypeList=menuService.getDishType(shopId);
        map.put("dishTypeList",dishTypeList);
        map.put("shopId",shopId);
        map.put("menus",menuList);
        return new ModelAndView("/food/list",map);

    }

    /**
     * 查询座位
     * @param shopId  店铺id
     * @param map     座位集合
     * @return
     */
//    @GetMapping("")
//    public ModelAndView selectTable(@RequestParam("")Integer shopId,
//                                    Map<String,Object>map){
//        List<Table> tableList=tableService.selectListTable(shopId);
//        map.put("",tableList);
//        return  new ModelAndView("",map);
//    }

    /**
     * 后端接口
     * 根据shopid查询店铺信息
     * @param shopId 店铺id
     * @param map
     * @return
     */
//    @GetMapping("")
//    public ModelAndView selectshopl(@RequestParam()Integer shopId,
//                                    Map<String,Object>map){
//        List<Shop> shopList=shopService.Allselect(shopId);
//        map.put("" , shopList);
//        return new ModelAndView("",map);
//    }







    //______________________________________________________________________________________________________


    /**
     * 查看某一个商家的某一分类的菜品信息
     *
     * @param request
     * @return
     */
    @GetMapping("/select")
    public Result selectMenu(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer dishType = Integer.parseInt(request.getParameter("typeId"));
        List<Menu> menuList = menuService.select(shopId, dishType);
        if (menuList.isEmpty()) {
            return new Result(202, "该商家尚未添加菜单信息");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Menu menu : menuList) {
            Map<String, Object> map = new HashMap<>();
            map.put("menuId", menu.getId());
            map.put("dishName", menu.getDishName());
            map.put("dishPrice", menu.getDishPrice());
            map.put("dishImage", menu.getDishImage());
            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return new Result(200, jsonArray, "菜单列表已发送");
    }

    /**
     * 查看某一个商家的菜品信息
     *
     * @param request
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectMenuAll(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        List<DishType> typeList = menuService.getDishType(shopId);
        List<net.sf.json.JSONObject> list = new ArrayList<>();
        for (DishType dishType : typeList) {
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            jsonObject.put("dishType", dishType.getDishType());
            jsonObject.put("dish", menuService.selectAll(shopId, dishType.getId()));
            list.add(jsonObject);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return new Result(200, jsonArray, "菜单列表已发送");
    }




    /**
     * 添加商家的菜单分类
     *
     * @param request
     * @return
     */
    @PostMapping("/dishType/add")
    public Result addDishType(HttpServletRequest request) {
        JSONObject result = StringToObject.StringToJSon(request);
        List<String> list = (List<String>) JSONArray.toList(JSONArray.fromObject(result.getString("goodsArray")), String.class);
        Integer shopId = Integer.parseInt(result.getString("shopId"));
        int i = 0;
        for (String str : list) {
            menuService.addDishType(shopId, str);
            i++;
        }
        if (i == list.size()) {
            return new Result(200, "菜品分类添加成功");
        } else {
            menuMapper.deleteDishTypeAll(shopId);
            return new Result(203, "菜品分类添加失败");
        }
    }

    /**
     * 查看商家的分类信息
     *
     * @param request
     * @return
     */
    @GetMapping("/dishType/select")
    public Result selectDishType(HttpServletRequest request) {
        List<DishType> typelist = menuService.getDishType(Integer.parseInt(request.getParameter("shopId")));
        if (typelist.isEmpty()) {
            return new Result(202, "商家尚未添加分类信息");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (DishType dishType : typelist) {
            Map<String, Object> map = new HashMap<>();
            map.put("menuTypeId", dishType.getId());
            map.put("typeName", dishType.getDishType());
            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return new Result(200, jsonArray, "菜单分类列表已发送");
    }

    @GetMapping("/dishType/update")
    public Result updateDishType(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        String typeName = request.getParameter("typeName");
        if (menuService.updateDishType(shopId, typeId, typeName) > 0) {
            return new Result(200, "分类信息修改成功");
        } else {
            return new Result(202, "分类信息修改失败");
        }
    }

    @GetMapping("/dishType/delete")
    public Result deleteDishType(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        if (menuService.deleteDishType(shopId, typeId) > 0) {
            return new Result(200, "分类信息删除成功");
        } else {
            return new Result(202, "分类信息删除失败");
        }
    }


    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}
