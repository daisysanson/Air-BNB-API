package hello.config;


import hello.component.CustomAuthHandler;
import hello.service.SiteUserDetailsService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthHandler customAuthHandler;
    

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

///login is broken ;( 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/apartment/**").access("USER_HOST")
                .antMatchers("/registrationForm/**").permitAll()
                .antMatchers("/registrationResult/**").permitAll()
                .antMatchers("/dashboard/**").permitAll().anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(customAuthHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")

                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling().and()

                .exceptionHandling();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "css/css/**", "js/js/**", "/images/**", "/vendor/ **");
    }

}

//                Do we want this at later date
//                .deleteCookies("my-remember-me-cookie")
//                .permitAll()
//                .and()
//                .rememberMe()
//                //.key("my-secure-key")
//                .rememberMeCookieName("my-remember-me-cookie")
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(24 * 60 * 60)
//                .and()