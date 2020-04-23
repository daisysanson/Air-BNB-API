//package hello.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.thymeleaf.spring5.ISpringTemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//@Configuration
//public class AppConfig {
//    public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
//
//        private ApplicationContext applicationContext;
//
//        public void setApplicationContext(ApplicationContext applicationContext) {
//            this.applicationContext = applicationContext;
//        }
//    }
//}