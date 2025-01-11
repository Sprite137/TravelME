package org.example.service;

import org.example.entity.ResponseResult;

public interface TravelLogService {
    ResponseResult getTravelLogByUserId(String userId);

    ResponseResult getLogLatLng(String userId);
}
