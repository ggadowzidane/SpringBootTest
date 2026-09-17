package exam.web.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import exam.web.jwt.JwtTokenProvider;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* EX) Authorization: Bearer eyJhbGciOiJIUzI1NiJ9....*/
		
		String authorization =
                request.getHeader("Authorization");

        if (authorization != null
                && authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

            if (jwtTokenProvider.validateToken(token)) {

                String loginId =
                        jwtTokenProvider.getLoginId(token);

                //요청을 보낸 사용자는 loginId이고 인증된 사용자라는 것을 Spring Security가 이해할 수 있는 형태로 만드는 작업.
                //아직 권한 기능을 만들지 않아서 3번째파라미터는 빈 Collection으로 처리
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                loginId,
                                null,
                                Collections.emptyList());
                //스프링 Security의 현재 요청에 대한 인증정보를 저장
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                /*
                 * JWT
					 ↓
					loginId = test
					 ↓
					Authentication 객체 생성
					 ↓
					SecurityContext에 저장
                 * */
            }
        }

        filterChain.doFilter(request, response);
	}

}
