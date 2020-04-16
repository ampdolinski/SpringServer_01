package main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login-form", "/register").permitAll()
                .antMatchers("/userPanel").hasRole("USER")
                .antMatchers("/users").hasRole("SERVER")
                .and()
                .formLogin().loginPage("/login-form").permitAll() // przekierowanie na widok logowania
                .loginProcessingUrl("/login").permitAll()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}