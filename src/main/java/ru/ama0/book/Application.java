package ru.ama0.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 

@SpringBootApplication //аннотация которая обозначает, что это spring boot приложение
// а значит, что программа должна быть запущена как spring boot.
@EnableAutoConfiguration//включает автоконфигурацю и тем самым избавляет нас от написания
// дополнительных классов конфигураций. По желанию можно написать свою конфигурацию. 
// Приложение выберет именно вашу кастомную конфигурацию
@EnableJpaRepositories(basePackages = "ru.ama0.book.repository") // Расположение репозиториев JPA
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);// Запуск приложения spring.

	}
}
