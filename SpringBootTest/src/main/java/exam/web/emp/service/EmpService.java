package exam.web.emp.service;

import exam.web.emp.dto.Emp;

public interface EmpService {
	public Emp selectEmpByLoginId(String loginId);
}
