package kr.co.farm.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardVO {
	
	private int board_id, board_type_id, board_readcnt, no, notifycnt;
	private String board_title, board_content, board_writer, type_name;
	private Date board_writedate;	
}
