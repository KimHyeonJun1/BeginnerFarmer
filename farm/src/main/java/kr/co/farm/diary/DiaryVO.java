package kr.co.farm.diary;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DiaryVO {
	private int diary_id;
	private String diary_title, diary_content, writer  ;
	private Date diary_writedate ; 
}



