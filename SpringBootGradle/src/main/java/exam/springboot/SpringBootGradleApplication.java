package exam.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 시작 흐름
	main()
	  ↓
	SpringApplication.run()
	  ↓
	Spring Boot 시작
	  ↓
	ApplicationContext 생성
	  ↓
	Bean 탐색/등록
	  ↓
	Auto Configuration
	  ↓
	내장 Tomcat 시작
	  ↓
	8080 포트 대기
*/
@SpringBootApplication //어플리케이션 시작
public class SpringBootGradleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootGradleApplication.class, args);
	}
}
