package kr.co.farm.plants;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter	@Setter
public class PlantsVO {
//작물관리
	
	private int plant_id, standard_temp, starndard_hum;
	private String plant_name, feature, food, file_name, filepath, plant_groth;
	private Date planting_date; 
	
	
}
