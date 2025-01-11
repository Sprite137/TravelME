package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.pojo.StationInfo;

import java.util.List;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 18:58
 */
@Mapper
public interface StationInfoMapper {
    StationInfo getStationInfo(String station);

    void insertStationInfo(StationInfo stationInfo);

    void insertAirStationInfo(StationInfo stationInfo);
}
