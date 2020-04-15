package hello;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
    public class AirBnbApplication{


    public static void main(String[] args) {
            SpringApplication.run(AirBnbApplication.class, args);
            BasicConfigurator.configure();
        }
    }
