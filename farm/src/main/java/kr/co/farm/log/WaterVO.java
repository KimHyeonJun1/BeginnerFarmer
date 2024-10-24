package kr.co.farm.log;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WaterVO {
	private int water_id, soilmoisture, plants_id;
	private Date waterdate;
	
}
