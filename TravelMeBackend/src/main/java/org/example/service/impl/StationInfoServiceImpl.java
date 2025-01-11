package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.entity.ResponseResult;
import org.example.entity.pojo.AirStationInfo;
import org.example.entity.pojo.StationInfo;
import org.example.mapper.StationInfoMapper;
import org.example.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.util.CSVParse.getCsvDataMethod3;
import static org.example.util.JsonParse.getJsonString;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 19:05
 */

@Service
public class StationInfoServiceImpl implements StationInfoService {

    @Autowired
    private StationInfoMapper stationInfoMapper;

    @Override
    public ResponseResult getStationInfoByName(String stationName) {
        return ResponseResult.SuccessResult(stationInfoMapper.getStationInfo(stationName));
    }

    @Override
    public void insertStationInfo() {
//        List<StationInfo> stationInfos = JSON.parseArray(getJsonString(), StationInfo.class);
//        for(int i = 0; i<stationInfos.size();i++ ){
//            stationInfoMapper.insertStationInfo(stationInfos.get(i));
//            Thread.sleep(50);
//        }

        return;
    }

    @Override
    public void insertAirStationInfo() {
        String fileName = "F:\\code\\all\\TravelMe\\TravelMeBackend\\src\\main\\resources\\WorldAirPortCode4(ICAO).csv";
        List<StationInfo> csvDataMethod3 = getCsvDataMethod3(fileName);
        for(int i = 0; i<csvDataMethod3.size();i++ ){
            stationInfoMapper.insertAirStationInfo(csvDataMethod3.get(i));
        }
//        System.err.println(csvDataMethod3);
    }
}
