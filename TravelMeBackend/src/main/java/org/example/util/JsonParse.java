package org.example.util;

import com.alibaba.fastjson.JSON;
import org.example.entity.pojo.StationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/5 19:30
 */
public class JsonParse {

    public static String path = "F:\\code\\all\\TravelMe\\TravelMeBackend\\src\\main\\resources\\station_lnglat.json";

    public static String getJsonString() {
        BufferedReader bufferedReader = null;
        String len = null;
        StringBuilder de = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(path));

            while ((len = bufferedReader.readLine()) != null) {
                de.append(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        assert de != null;
        return de.toString();
    }



    public static void main(String[] args) {
        List<StationInfo> stationInfos = JSON.parseArray(getJsonString(), StationInfo.class);
        System.out.println(stationInfos.get(1));
    }
}
