package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.pojo.TravelLog;
import org.example.entity.vo.TravelLogVo;

import java.util.List;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 20:16
 */
@Mapper
public interface TravelLogMapper {

    List<TravelLog> getTravelLogsByUserId(String userId);

    void insertTravelLog(TravelLogVo travelLogVo);

}
