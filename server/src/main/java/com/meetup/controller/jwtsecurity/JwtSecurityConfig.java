package com.meetup.controller.jwtsecurity;

import com.meetup.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * . set the security for rest api
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * . jwttokenprovide, custom class
     */
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    /**
     * . UserDetails Service, custom class
     */
    @Autowired
    private AuthenticationService customUserDetailsService;

    /**
     * . get AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * . set the UserDetailsService
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception exception
     */
    @Autowired
    public void configAuthentication(final AuthenticationManagerBuilder auth)
        throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * . set explicitly security params and tokenprovider
     *
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
            .headers()
            .frameOptions()
            .disable()//this one to enable /h2 console in browser
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }

    /**
     * . set CustomAuthenticationFailureHandler
     *
     * @return CustomAuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
