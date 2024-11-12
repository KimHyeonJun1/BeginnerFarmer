package kr.co.farm.relay;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RelayVO {
 private int log_numb, plant_id, temperature, humid, bright, moisture;
 private String mac_address, userid, light, relay1, relay2, relay3, relay4;
 private Date time_log;
}
