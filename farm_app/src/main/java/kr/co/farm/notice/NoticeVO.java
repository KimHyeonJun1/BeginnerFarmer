package kr.co.farm.notice;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
	private int id, readcnt, no;
	private String title, content, writer, name, filename, filepath, writedate;
}
