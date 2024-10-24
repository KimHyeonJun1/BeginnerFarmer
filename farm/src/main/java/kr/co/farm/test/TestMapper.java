package kr.co.farm.test;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

	String today();
}
