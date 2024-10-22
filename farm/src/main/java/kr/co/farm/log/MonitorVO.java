package kr.co.farm.log;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonitorVO {
 private int log_id, plant_id, temperature, humid, bright, moisture;
 private Date time_log, cctv, planting_date;
}
