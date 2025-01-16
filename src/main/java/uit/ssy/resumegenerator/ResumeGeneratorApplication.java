package uit.ssy.resumegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@   EnableAsync
public class ResumeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeGeneratorApplication.class, args);
    }

}
