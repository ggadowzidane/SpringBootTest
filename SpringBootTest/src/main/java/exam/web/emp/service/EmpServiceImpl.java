package exam.web.emp.service;

import org.springframework.stereotype.Service;
import exam.web.emp.dto.Emp;
import exam.web.emp.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService{

	private final EmpMapper empMapper;
	
	public EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	
	@Override
	public Emp selectEmpByLoginId(String loginId) {
		return this.empMapper.selectEmpByLoginId(loginId);
	}

	
}
