package com.ruoyi.web.controller;

import com.ruoyi.common.base.Result;
import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.domain.FeedBack;
import com.ruoyi.system.service.FeedBackService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("feedback")
public class FeedBackController {

    @Autowired
    FeedBackService feedBackService;

    /**
     * 添加反馈
     * @param request
     * @return
     */
    @GetMapping("/add")
    public Result addFeedBack(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String openId = request.getParameter("openId");
        String remark = request.getParameter("remark");
        int rows = feedBackService.add(shopId, openId, remark);
        if (rows > 0) {
            return new Result(200, "反馈添加成功");
        } else {
            return new Result(202, "反馈添加失败");
        }
    }

    /**
     * 查询反馈
     * @param request
     * @return
     */
    @GetMapping("/select")
    public Result selectFeedBack(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String openId = request.getParameter("openId");
        List<FeedBack> feedBackList = feedBackService.select(shopId, openId);
        if (feedBackList.isEmpty()) {
            return new Result(203, "该用户未添加过反馈");
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (FeedBack feedBack : feedBackList) {
            Map<String, Object> map = new HashMap<>();
            map.put("remark", feedBack.getRemark());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        if (jsonarr.isEmpty()) {
            return new Result(211, "该用户没有数据");
        } else {
            return new Result(200, jsonarr, "成功");
        }
    }

    /**
     * 修改反馈
     * @param request
     * @return
     */
    @GetMapping("/update")
    public Result updateFeedBack(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String openId = request.getParameter("openId");
        String remark = request.getParameter("remark");
        int  rows = feedBackService.update(shopId, openId,remark);
        if (rows > 0) {
            return new Result(200, "反馈修改成功");
        } else {
            return new Result(202, "反馈修改失败");
        }
    }

    /**
     * 删除反馈
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Result deleteFeedBack(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String openId = request.getParameter("openId");

        int  rows = feedBackService.delete(shopId, openId);
        if (rows > 0) {
            return new Result(200, "反馈删除成功");
        } else {
            return new Result(202, "反馈删除失败");
        }
    }


}
