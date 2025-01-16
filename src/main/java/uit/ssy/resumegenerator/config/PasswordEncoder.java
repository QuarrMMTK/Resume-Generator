package uit.ssy.resumegenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 13:33
 * Project Name : ResumeGenerator
 **/
@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
