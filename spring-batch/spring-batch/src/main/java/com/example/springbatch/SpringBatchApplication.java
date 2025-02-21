package com.example.springbatch;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.example.springbatch;  // Ensure this matches your project structure



@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}


