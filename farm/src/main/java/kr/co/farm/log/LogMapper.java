package kr.co.farm.log;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper   {
	 //CRUD
	   
	   List<LogVO> getListOfLog(); //로그 목록조회
	   
}
