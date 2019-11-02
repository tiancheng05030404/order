package com.ruoyi.web.controller;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.ruoyi.common.base.StringToObject;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.form.UserForm;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.UserService;

import com.ruoyi.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private IOrderService orderService;

    //go登陆页面
    @GetMapping("/go")
    public ModelAndView go(){
        return new ModelAndView("/index/login");
    }

//    //go登陆页面
//    @GetMapping("/go")
//    public Result go(){
//        return new Result(200,"/index/login");
//    }



    //验证用户身份 跳转页面
    @PostMapping("/skip")
    public ResultVO<User> skip(HttpServletRequest request,
                               Map<String,Object> map){
        JSONObject json= StringToObject.StringToJSon(request);
        UserForm userform=new UserForm();
        userform.setUserName(json.getString("userName"));
        userform.setPassword(json.getString("password"));
        User alluser=userService.selectUser(userform.getUserName(),userform.getPassword());
        return  new ResultVO<User>(200,"成功",alluser);

    }




        //验证用户身份 跳转页面
   @PostMapping("/skips")
                 public ModelAndView skip(@Valid UserForm userform,
                                          Map<String,Object> map,
                                          HttpSession session){
         List<User> userList=userService.findAll();
        for (User user : userList) {
            if (user.getUserName().equals(userform.getUserName())&&user.getPassword().equals(userform.getPassword())){
                if (user.getUserType()==1){
                    //服务员账号登录
                    //跳转服务员管理界面（显示订单）
                    List<Order> orderList=orderService.finAll(user.getSid());
                    map.put("orderList",orderList);
                    map.put("shopId",user.getSid());
                    session.setAttribute("shopId",user.getSid());
                    return new ModelAndView("order/list",map);
                } else if (user.getUserType()==0){
                    map.put("shopId",user.getSid());
                    session.setAttribute("shopId",user.getSid());
                    return new ModelAndView("order/list",map);
                }else {
                    String url="order/list";
                    map.put("url",url);
                    map.put("shopId",user.getSid());
                    session.setAttribute("shopId",user.getSid());
                    return new ModelAndView("order/list",map);
                }
            }
        }
        String url="go";
        String msg="账号密码有误";
        map.put("msg",msg);
        map.put("url",url);
        return new ModelAndView("/common/error",map);
    }
}
