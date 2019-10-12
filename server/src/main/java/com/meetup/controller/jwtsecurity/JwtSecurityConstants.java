package com.meetup.controller.jwtsecurity;


/**.
 * constants as Secret and Validity
 */
public final class JwtSecurityConstants {
    /**.
     * constructor (closed)
     */
    private JwtSecurityConstants() {
        //not called
    }
    /**.
     * key that will be encoded in tokenProvider
     */
    public static final String SECRET = "mysecret";
    /**.
     * for what time is token valid
     */
    public static final long VALIDITY_IN_MS = 7200000; //2hours
}
