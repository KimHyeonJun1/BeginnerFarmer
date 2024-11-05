package kr.co.farm.log;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TemperatureVO {
 private int log_numb, plant_id, temperature, humid, bright, moisture;
 private String mac_address, userid;
 private Date time_log, cctv;
}
