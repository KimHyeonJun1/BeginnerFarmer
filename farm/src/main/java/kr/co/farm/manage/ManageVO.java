package kr.co.farm.manage;


import lombok.Getter;
import lombok.Setter;

@Getter	@Setter
public class ManageVO {
	private int plant_id, standard_temp, standard_hum, standard_soil, plantid_log;
	private String plant_name, feature, plant_groth, food, file_name, filepath, userid_log;
	
}