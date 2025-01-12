package org.example.entity.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 23:50
 */
@Data
public class TravelLatLngDto {
    String startStation;

    String endStation;

    String startStationLat;

    String startStationLng;

    String endStationLat;

    String endStationLng;

    int isAirPlane;

    Timestamp travelTime;
}
