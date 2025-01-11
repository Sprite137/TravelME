package org.example.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.example.entity.pojo.StationInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.pojo.AirStationInfo;

/**
 * @author sprite-pc
 * @description:
 * @date 2025/1/11 15:24
 */
public class CSVParse {

    public static <T> List<T> getCsvDataMethod3(String fileName) {
        List<StationInfo> res = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            List<StationInfo> stationInfos = new CsvToBeanBuilder<StationInfo>(reader)
                    .withType(StationInfo.class)
                    .build()
                    .parse();

            for (StationInfo stationInfo : stationInfos) {
                res.add(stationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (List<T>) res;
    }

    public static void main(String[] args) {
        String fileName = "F:\\code\\all\\TravelMe\\TravelMeBackend\\src\\main\\resources\\WorldAirPortCode4(ICAO).csv";
        List<AirStationInfo> csvDataMethod3 = getCsvDataMethod3(fileName);
        System.err.println(csvDataMethod3);

    }
}
