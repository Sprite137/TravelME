package org.example.controller;

import org.example.entity.ResponseResult;
import org.example.entity.vo.TravelLogVo;
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


    @GetMapping("/queryTravelLogs")
    public ResponseResult getLog(@RequestParam("userId") String userId){
        return travelLogService.getLogLatLng(userId);
    }

    @GetMapping()
    public ResponseResult getLogLatLng(@RequestParam("userId") String userId){
        return travelLogService.getLogLatLng(userId);
    }

    @PostMapping("/addTravelLog")
    public ResponseResult addTravelLog(@RequestBody TravelLogVo travelLogVo){
        return travelLogService.addTravelLog(travelLogVo);
    }

}
