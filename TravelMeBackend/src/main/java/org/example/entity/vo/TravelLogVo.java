package org.example.entity.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/13 19:20
 */
@Data
public class TravelLogVo {
    String userId;

    String startStation;

    String endStation;

    Timestamp travelTime;

    Integer isAirPlane;
}
