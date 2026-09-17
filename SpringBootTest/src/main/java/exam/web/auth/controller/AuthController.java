package exam.web.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.web.auth.dto.LoginRequest;
import exam.web.emp.dto.Emp;
import exam.web.emp.service.EmpService;
import exam.web.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final EmpService empService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider; 
	
    public AuthController(
    		EmpService empService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.empService = empService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request) {

        Emp emp = empService.selectEmpByLoginId(request.getLoginId());

        if (emp == null) {
            return ResponseEntity
                    .status(401)
                    .body("아이디가 존재하지 않습니다.");
        }

        if (!passwordEncoder.matches(
                request.getLoginPwd(),
                emp.getLoginPwd())) {
            return ResponseEntity
                    .status(401)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        String accessToken =
                jwtTokenProvider.createToken(emp.getLoginId());

        return ResponseEntity.ok(accessToken);
    }
}
