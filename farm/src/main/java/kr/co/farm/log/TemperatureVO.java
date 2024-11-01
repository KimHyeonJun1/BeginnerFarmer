package kr.co.farm.log;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TemperatureVO {
 private int log_id, plant_id, temperature, humid, bright, moisture, water_id, soilmoisture;
 private Date time_log, cctv, planting_date, waterdate;
}