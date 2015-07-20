package org.itechart.config.security;

import org.itechart.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    public UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(false)
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint("basic"))
                .and().authorizeRequests()
                .antMatchers(
                        "/"
                        , "/views/**"
                        , "/scripts/**"
                        , "/styles/**"
                        , "/images/**"
                        , "/webjars/**"
                        , "/rest/api/auth/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/")
                .and().addFilterAfter(new CustomCsrfHeaderFilter(), CsrfFilter.class)
                .logout().permitAll()
                .and().csrf().csrfTokenRepository(CustomCsrfTokenRepository.get())
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
    }



}