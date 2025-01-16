package org.example.controller;

import org.example.entity.ResponseResult;
import org.example.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 19:02
 */
@RestController
@RequestMapping("/stationInfo")
@CrossOrigin
public class StationInfoController {

    @Autowired
    private StationInfoService stationInfoService;

    @GetMapping("")
    public ResponseResult getStationInfoByName(@RequestParam("stationName") String stationName){
        return  stationInfoService.getStationInfoByName(stationName);
    }

    @GetMapping("/test")
    public ResponseResult getLog() {
//        stationInfoService.insertStationInfo();
        stationInfoService.insertAirStationInfo();
        return ResponseResult.SuccessResult("hello, insert success!");
    }

    @GetMapping("/findRecommendStationsByName")
    public ResponseResult findRecommendStationByName(@RequestParam("stationName") String stationName,@RequestParam("isAirStation") Integer isAirStation) {
        return stationInfoService.findRecommendStationsByName(stationName,isAirStation);
    }
}
