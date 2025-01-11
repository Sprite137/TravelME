package org.example.entity.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 18:53
 */
@Data
public class TravelLog {
    int id;

    int userId;

    String startStation;

    String endStation;

    int isAirPlane;

    int isDel;

    Date createTime;

    String createBy;

    Date updateTime;

    String updateBy;

    Date travelTime;
}
