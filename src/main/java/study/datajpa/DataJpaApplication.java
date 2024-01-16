package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware(){
		/*
			return new AuditorAware<String>(){
				@Override
				public Optinal<String> getCurrentAuditor(){
					return Optional.of(UUID.randomUUID().toString());
				}
		 */
		// 실제로는 저 UUID부분에 SpringSecurity ContextHolder에서 Id 꺼내서 삽입!
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
