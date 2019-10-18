package com.meetup.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**.
 * Class to determine that to do if user without token
 */
@Component
public class RestAuthEntryPoint implements AuthenticationEntryPoint {
    /**.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authException AuthenticationException
     * @throws IOException exception
     */
    @Override
    public void commence(
      final  HttpServletRequest request,
      final    HttpServletResponse response,
      final  AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unauthorized");
    }

}
