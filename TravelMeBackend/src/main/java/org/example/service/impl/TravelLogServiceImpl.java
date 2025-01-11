package org.example.service.impl;

import org.example.entity.ResponseResult;
import org.example.entity.dto.TravelLatLngDto;
import org.example.entity.pojo.StationInfo;
import org.example.entity.pojo.TravelLog;
import org.example.mapper.StationInfoMapper;
import org.example.mapper.TravelLogMapper;
import org.example.service.StationInfoService;
import org.example.service.TravelLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 20:15
 */
@Service
public class TravelLogServiceImpl implements TravelLogService {

    @Autowired
    private TravelLogMapper travelLogMapper;

    @Autowired
    private StationInfoMapper stationInfoMapper;

    @Override
    public ResponseResult getTravelLogByUserId(String userId) {
        return ResponseResult.SuccessResult(travelLogMapper.getTravelLogsByUserId(userId));
    }

    @Override
    public ResponseResult getLogLatLng(String userId) {

        List<TravelLog> travelLogs = travelLogMapper.getTravelLogsByUserId(userId);
        List<TravelLatLngDto> travelLatLngDtos = travelLogs.stream().map(travelLog -> {
            TravelLatLngDto travelLatLngDto = new TravelLatLngDto();
            BeanUtils.copyProperties(travelLog,travelLatLngDto);
            StationInfo StartStation = stationInfoMapper.getStationInfo(travelLog.getStartStation());
            StationInfo EndStation = stationInfoMapper.getStationInfo(travelLog.getEndStation());
            travelLatLngDto.setStartStationLat(StartStation.getLat());
            travelLatLngDto.setStartStationLng(StartStation.getLng());
            travelLatLngDto.setEndStationLat(EndStation.getLat());
            travelLatLngDto.setEndStationLng(EndStation.getLng());
            return travelLatLngDto;
        }).collect(Collectors.toList());

        return ResponseResult.SuccessResult(travelLatLngDtos);
    }
}
