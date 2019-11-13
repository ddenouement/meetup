package com.meetup.controller.security.jwt;

import com.meetup.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * . set the security for rest api
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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


    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * . get AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
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
        auth.userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder);
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
                .and().httpBasic().disable()
          //     .csrf().disable()//TODO it is disabled for testing in postman (erase when application is ready!)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
        //TODO un-comment to enable CSRF
                    .and()
                     .csrf()
                .ignoringAntMatchers("/user/login", "/", "/user/logout",  "/api/v1/user/login","/api/v1/user/logout")
               // .requireCsrfProtectionMatcher (new AllExceptUrlStartedWith("/","/login","/logout","/api/v1/user/login","/api/v1/user/logout","/api/v1/user/profile"))
                 .csrfTokenRepository (this.getCsrfTokenRepository());
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
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
