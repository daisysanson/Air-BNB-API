package hello.config;


import hello.service.SiteUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    public SiteUserDetailsService mongoUserDetails() {
        return new SiteUserDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication();
        SiteUserDetailsService siteUserDetailsService = mongoUserDetails();
        auth.userDetailsService(siteUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/resources/**", "/static/**", "css/css/**", "js/js/**", "/images/**", "/vendor/ **").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/registrationForm/**").permitAll()
                .antMatchers("/registrationResult/**").permitAll()
                .antMatchers("/error/**, /badRequest/ , /notFound/").permitAll()
                .antMatchers("/booking/**").hasAuthority("USER_CUSTOMER")
                .antMatchers("/newBookingCreate/**,/getBooking/**").authenticated()
                .antMatchers("/deleteBookingForm/**").authenticated()
                .antMatchers("/newBookingCreate/**").authenticated()
                .antMatchers("/getBooking/**").authenticated()
                .antMatchers("/account/**").authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error=true").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")

                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index").and().exceptionHandling().and()

                .exceptionHandling();
    }

    @Override
     public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "css/css/**", "js/js/**", "/images/**", "/vendor/ **");
    }


}
