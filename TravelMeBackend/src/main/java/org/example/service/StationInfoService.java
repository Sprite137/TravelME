package org.example.service;

import org.example.entity.ResponseResult;
import org.springframework.stereotype.Service;


public interface StationInfoService {
    ResponseResult getStationInfoByName(String stationName);

    void insertStationInfo();

    void insertAirStationInfo();

    ResponseResult findRecommendStationsByName(String stationName, Integer isAirStation);
}
