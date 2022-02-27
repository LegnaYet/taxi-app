package com.legnayet.taxiapp.device.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.legnayet.taxiapp.common.BaseController;
import com.legnayet.taxiapp.device.entity.Device;
import com.legnayet.taxiapp.device.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("device")
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备
     * @return
     */
    @GetMapping("page")
    public Map<String,Object> getDevicePageList(@RequestParam("isOnline")String isOnline,
                                                @RequestParam("pageNum")Integer pageNum,
                                                @RequestParam("pageSize")Integer pageSize){
        try {
            IPage<Device> results = deviceService.getDevicePageList(isOnline,pageNum,pageSize);
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
    public Map<String,Object> getDeviceList(){
        try {
            Map<String, Object> results = deviceService.getDeviceList();
            return data(results);
        } catch (Exception e) {
            log.error("查询设备时发生错误!", e);
            return fail();
        }
    }
}
