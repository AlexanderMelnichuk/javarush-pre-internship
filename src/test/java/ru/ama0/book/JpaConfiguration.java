package ru.ama0.book;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "ru.ama0.book.repository")
@EntityScan({"ru.ama0.book.entity", "ru.ama0.book.dto"})
public class JpaConfiguration {

}
