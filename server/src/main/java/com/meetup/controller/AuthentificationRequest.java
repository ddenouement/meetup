package com.meetup.controller;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**.
        * Represents a JSON object with Login and Password
 */
    public class AuthentificationRequest implements Serializable {

        /**.
         * login
         */
        private String login;

        /**.
         * password
         */
        private String password;
    }
