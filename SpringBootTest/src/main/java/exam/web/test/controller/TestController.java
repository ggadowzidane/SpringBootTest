package exam.web.test.controller;

import java.util.Collection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.web.emp.dto.Emp;

@RestController
public class TestController {
	
	private final PasswordEncoder passwordEncoder;
	
	public TestController(PasswordEncoder passwordEncoder) {
	    this.passwordEncoder = passwordEncoder;
	}

	
	@GetMapping("/test")
	public void test() {
		System.out.println("hello world");
	}
	
	@GetMapping("/test/password")
	public String password() {
		return passwordEncoder.encode("1234");
	}
	
}
