package kr.co.farm.arduino;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlantLogVO {
	private int log_numb, bright, plant_id, moisture;  
	private String mac_address, userid, light, relay1, relay2, relay3, relay4;
	private Date time_log;
	private double temperature, humid;
}
