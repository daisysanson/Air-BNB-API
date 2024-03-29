package hello;

import hello.dao.RoleRepository;
import hello.model.Role;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@SpringBootApplication
public class AirBnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirBnbApplication.class, args);
        BasicConfigurator.configure();

    }

    @Bean
    public static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public static LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public static CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Role userCustomer = roleRepository.findByRole("USER_CUSTOMER");
            if (userCustomer == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER_CUSTOMER");
                roleRepository.save(newUserRole);
            }
            Role userHostRole = roleRepository.findByRole("USER_HOST");
            if (userHostRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER_HOST");
                roleRepository.save(newUserRole);
            }
        };
    }
}