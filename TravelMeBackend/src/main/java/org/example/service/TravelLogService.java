package org.example.service;

import org.example.entity.ResponseResult;
import org.example.entity.vo.TravelLogVo;

public interface TravelLogService {
    ResponseResult getTravelLogByUserId(String userId);

    ResponseResult getLogLatLng(String userId);

    ResponseResult addTravelLog(TravelLogVo travelLogVo);
}
