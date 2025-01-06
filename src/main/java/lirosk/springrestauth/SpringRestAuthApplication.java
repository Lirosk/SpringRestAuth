package lirosk.springrestauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringRestAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestAuthApplication.class, args);
    }
}
