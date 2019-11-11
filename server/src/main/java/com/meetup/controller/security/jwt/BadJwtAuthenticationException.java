package com.meetup.controller.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**.
 * class of JWT exception
 */
public class BadJwtAuthenticationException extends AuthenticationException {
    /**.
     * uid
     */
    private static final long serialVersionUID = -761503632186596342L;

    /**.
     * constructor.
     * @param e String
     */
    public BadJwtAuthenticationException(final String e) {
        super(e);
    }
}
