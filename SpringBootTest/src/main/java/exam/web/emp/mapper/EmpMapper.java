package exam.web.emp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import exam.web.emp.dto.Emp;

@Mapper
public interface EmpMapper {

	public Emp selectEmpByLoginId(@Param("loginId") String loginId);
}
