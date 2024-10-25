package kr.co.farm.guide;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GuideVO {
	//작물가이드
	private int plant_id;
	private double standard_temp, standard_hum, standard_soil;
	private String plant_name, feature, plant_groth, food, image_path;
}
