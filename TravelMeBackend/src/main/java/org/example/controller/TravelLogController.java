package org.example.controller;

import org.example.entity.ResponseResult;
import org.example.service.TravelLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 17:44
 */
@RestController
@RequestMapping("/travelLog")
@CrossOrigin
public class TravelLogController {

    @Autowired
    private TravelLogService travelLogService;


    @GetMapping("/test")
    public ResponseResult getLog(){
        return travelLogService.getLogLatLng("1");
    }

    @GetMapping()
    public ResponseResult getLogLatLng(@RequestParam("userId") String userId){
        return travelLogService.getLogLatLng(userId);
    }

}
