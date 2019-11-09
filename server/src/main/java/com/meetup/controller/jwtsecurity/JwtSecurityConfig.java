package com.meetup.controller.jwtsecurity;

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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

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
                .and().httpBasic().disable()
                .csrf().disable()//TODO it is disabled for testing in postman (erase when application is ready!)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //TODO un-comment to enable CSRF
        //            .and()
        //            .csrf()
        //         .requireCsrfProtectionMatcher (new AllExceptUrlStartedWith("/api/v1/user/login","/api/v1/user/logout"))
        //          .csrfTokenRepository (this.getCsrfTokenRepository());
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }

    private static class AllExceptUrlStartedWith implements RequestMatcher {

        private static final String[] ALLOWED_METHODS =
                new String[]{"GET"};

        private final String[] allowedUrls;

        public AllExceptUrlStartedWith(String... allowedUrls) {
            this.allowedUrls = allowedUrls;
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            String method = request.getMethod();
            for (String allowedMethod : ALLOWED_METHODS) {
                if (allowedMethod.equals(method)) {
                    return false;
                }
            }

            String uri = request.getRequestURI();
            for (String allowedUrl : allowedUrls) {
                if (uri.startsWith(allowedUrl)) {
                    return false;
                }
            }
            return true;
        }

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
