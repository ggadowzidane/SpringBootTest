package exam;

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
@SpringBootApplication
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
