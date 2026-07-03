package andreasaderi.L5.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class ConfigClass {
    @Bean
    public Faker getFaker() {
        return new Faker(Locale.ITALY);
    }
}
