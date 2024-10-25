package kr.co.exam.product.management;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//main함수가 있는 Application 클래스에 추가함
	//ModelMapper를 사용하기 위해 매번 new키워드로 ModelMapper를 생성하기 보단
	//ModelMapper 클래스의 인스턴스를 생성한 후 빈으로 등록함
	@Bean
	public ModelMapper modelMapper() {

		//아래와 같이 설정해주어야 getter와 setter가 없어도 변환해줌
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);

		return modelMapper;
	}

	//데이터베이스 접속 테스트를 위한 코드
	@Bean
	@Profile("prod")
	public ApplicationRunner runner(DataSource dataSource) {
		return args -> {
			Connection connection = dataSource.getConnection();
		};
	}
}
