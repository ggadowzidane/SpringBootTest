package exam.web.emp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.web.emp.dto.Emp;
import exam.web.emp.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	private final EmpService empService;
	
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	@GetMapping("/{loginId}")
	public Emp getEmp(@PathVariable String loginId) {
		return this.empService.selectEmpByLoginId(loginId);
	}
}
