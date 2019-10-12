package com.meetup.controller.jwtsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**.
 * implementation of AuthenticationFailureHandler
 */
public class CustomAuthenticationFailureHandler implements
    AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**.
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(
      final   HttpServletRequest request,
    final    HttpServletResponse response,
     final   AuthenticationException exception)
        throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
            "timestamp",
            Calendar.getInstance().getTime());
        data.put(
            "exception",
            exception.getMessage());

        response.getOutputStream()
            .println(objectMapper.writeValueAsString(data));
    }
}