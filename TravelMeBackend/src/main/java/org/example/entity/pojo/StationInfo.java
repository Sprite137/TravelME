package org.example.entity.pojo;

import lombok.Data;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 18:56
 */
@Data
public class StationInfo {
    int id;

    String stationName;

    String lat;

    String lng;

    String gCity;

    String gCode;

    String ICAO;

    Integer isAirStation;
}
