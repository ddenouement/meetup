package com.meetup.controller.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**.
 * set tokenprovider
 */
public class JwtConfigurer extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**.
     * token provider, custom class
     *
     */
    private JwtTokenProvider jwtTokenProvider;

    /**.
     *
     * @param mjwtTokenProvider init tokenprovider
     */
    public JwtConfigurer(final JwtTokenProvider mjwtTokenProvider) {
        this.jwtTokenProvider = mjwtTokenProvider;
    }

    /**.
     *set the jwttokenprovider
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter,
            UsernamePasswordAuthenticationFilter.class);
    }
}
