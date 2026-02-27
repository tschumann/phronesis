package phronesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.wuffesan.api")
public class Main {

    // entry-point
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
