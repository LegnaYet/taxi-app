package com.legnayet.taxiapp.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.legnayet.taxiapp.common.BaseController;
import com.legnayet.taxiapp.order.entity.Order;
import com.legnayet.taxiapp.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询设备
     * @return
     */
    @GetMapping("page")
    public Map<String,Object> getOrderPageList(@RequestParam("isShow")Integer isShow,
                                           @RequestParam("pageNum")Integer pageNum,
                                           @RequestParam("pageSize")Integer pageSize){
        try {
            IPage<Order> results = orderService.getOrderPageList(isShow,pageNum,pageSize);
            return data(results);
        } catch (Exception e) {
            log.error("查询设备时发生错误!", e);
            return fail();
        }
    }

    /**
     * 查询设备
     * @return
     */
    @GetMapping("list")
    public Map<String,Object> getOrderList(){
        try {
            Map<String, Object> results = orderService.getOrderList();
            return data(results);
        } catch (Exception e) {
            log.error("查询设备时发生错误!", e);
            return fail();
        }
    }

}
