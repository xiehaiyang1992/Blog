package club.xiehaiyang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import club.xiehaiyang.entity.SMem;

public interface SMemMapper {
	@Insert("insert into s_mem(used,total) value(#{sMem.used},#{sMem.total} )")
	public void insert(@Param("sMem") SMem sMem);

	@Select("select * from s_mem  WHERE time like '%0:00' order by id desc LIMIT 1100")
	List<SMem> selectMemUseInfo1week(@Param("sMem") SMem SMem);

	@Select("select * from s_mem  WHERE time like '%:00' order by id desc limit 1500")
	List<SMem> selectMemUseInfo1day(@Param("sMem") SMem SMem);

	@Select("select * from s_mem  order by id desc limit 120")
	List<SMem> selectMemUseInfo5min(@Param("sMem") SMem SMem);

}
