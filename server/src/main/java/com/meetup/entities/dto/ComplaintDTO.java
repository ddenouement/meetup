package com.meetup.entities.dto;

import lombok.Data;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ComplaintDTO {
    /**.
     * Id
     */
    private int id;
    /**.
     * Id of user who is complaining
     */
    private int idUserFrom;
    /**.
     * Id of user on whom is complaining
     */
    private int idUserTo;
    /**
     * Content .
     */
    private String content;
    /**.
     * time posted
     */
    private Date postedDate;

    /**.
     *  if admin has read this complaint
     */
    private boolean isRead;

}
