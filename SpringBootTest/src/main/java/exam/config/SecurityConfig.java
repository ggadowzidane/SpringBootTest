package exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import exam.web.filter.JwtAuthenticationFilter;
import exam.web.jwt.JwtAuthenticationEntryPoint;
import exam.web.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    public SecurityConfig(
    		JwtTokenProvider jwtTokenProvider,
    		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() //헤더에 JWT전달하는 API사용하기에 CSRF보호를 사용하지 않음
			.formLogin().disable() //기본 로그인 화면제거(Spring Security가 제공하는 거 사용안하려는거임)
			.httpBasic().disable() //http basic 인증 제거 EX) Authorization: Basic xxxxxxx 이런걸 사용하지않고
									//						Authorization: Bearer JWT 이런걸 사용함
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //사용자 인증 상태를 HTTP SEssion에 저장해서 관리하지 않는 설정.
			.and()
			
			//인증되지 않은 사용자가 인증이 필요한 API에 접근하면 JwtAuthenticationEntryPoint로 처리해라
			.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			
			//로그인 API만 인증 없이 허용. 그 외 모든 API 인증필요
			.authorizeHttpRequests()
				.antMatchers("/auth/login").permitAll()
				.anyRequest().authenticated()
			.and()
			
			//Spring Security에게:
			//UsernamePasswordAuthenticationFilter보다 먼저 우리가 만든 JwtAuthenticationFilter를 실행해라.
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
			
			//현재 요청흐름
			/**
			 * HTTP Request
				      ↓
				JwtAuthenticationFilter
				      ↓
				Authorization Header 확인
				      ↓
				JWT 검증
				      ↓
				SecurityContext에 Authentication 저장
				      ↓
				Spring Security 권한 검사
				      ↓
				Controller
			 * 
			 * */
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
