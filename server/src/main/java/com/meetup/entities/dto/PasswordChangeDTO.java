package com.meetup.entities.dto;

import lombok.Data;

@Data
public class PasswordChangeDTO {
    /**
     * old password.
     */
    private String oldPassword;
    /**
     * new password to set.
     */
    private String newPassword;
}
