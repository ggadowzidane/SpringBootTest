package exam.web.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//AuthenticationEntryPoint의 역할
/**
 * JWT 없음
   ↓
	JwtAuthenticationFilter
	Authentication 생성 안 함
	   ↓
	SecurityContext = 인증정보 없음
	   ↓
	/emp/test는 authenticated() 필요
	   ↓
	Spring Security가 인증되지 않았다고 판단
	   ↓
	AuthenticationEntryPoint
	   ↓
	401 Unauthorized
 * */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                "{\"message\":\"인증이 필요합니다.\"}"
        );

	}

}
