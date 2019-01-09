package club.xiehaiyang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import club.xiehaiyang.entity.SCpu;; 

public interface SCpuMapper {
	@Insert("insert into s_cpu(cpu0 ,cpu1) value(#{s_cpu.cpu0},#{s_cpu.cpu1})") 
	public void insert2Cpu(@Param("s_cpu") SCpu sCpu);
	
	@Select("SELECT * FROM s_cpu WHERE time like '%0:00' order by id desc LIMIT 1100")
	List<SCpu> selectCpuUseInfo1week(@Param("sCpu") SCpu SCpu);
	
	@Select("select * from s_cpu WHERE time like '%:00' order by id desc limit 1500")
	List<SCpu> selectCpuUseInfo1day(@Param("sCpu") SCpu SCpu);
	
	@Select("select * from s_cpu  order by id desc limit 120")
	List<SCpu> selectCpuUseInfo5min(@Param("sCpu") SCpu SCpu);
	
	
}
