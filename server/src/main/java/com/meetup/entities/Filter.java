package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

/**.
 * class representing filter
 */
@Data
public class Filter {
   private int id;
   private int  id_user;
   /**.
    * name given by user
    */
   private String name;
   private int id_language;
   private float rate_from;
   private float rate_to;
   private Date time_from;
   private Date time_to;
   private List<Integer> topics_ids;
   private List <Topic> topics;
   private String title_substring;
   public  Filter(){
      topics_ids = new ArrayList<Integer>()   ;
      topics = new ArrayList<Topic>();
   }
}
