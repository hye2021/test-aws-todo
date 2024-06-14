package org.example.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            String region = "ap-northeast-2";

            SsmClient ssmClient = SsmClient.builder()
                    .region(Region.of(region))
                    .build();

            System.out.println("[DB] username : " + getParameterValue(ssmClient, "/todo/config/DB_USERNAME"));
            System.out.println("[DB] password : " + getParameterValue(ssmClient, "/todo/config/DB_PASSWORD"));
        };
    }

    private String getParameterValue(SsmClient ssmClient, String parameterName){
        GetParameterRequest parameterRequest = GetParameterRequest.builder()
                .name(parameterName)
                .withDecryption(true)
                .build();

        GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
        return parameterResponse.parameter().value();
    }

}