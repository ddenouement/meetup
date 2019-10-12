package com.meetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**.
 * runner class for MeetUp
 */
@SpringBootApplication
public class MeetUpApplication {
    /**.
     * constructor, closed for calls
     */
    public MeetUpApplication(){
        //not user
    }
    /**.
     *main method
     * @param args String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(MeetUpApplication.class, args);
    }
}
/*
* url: jdbc:postgresql://salt.db.elephantsql.com/cpoomlzp
    password: QCwd3DcEysPt0euG0gTZyVGbJ6sEGcbP
    username: cpoomlzp
    continue-on-error: false
    schema: classpath:sql/Schema.sql
    initialization-mode: never*/
