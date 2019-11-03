package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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
   private LocalDateTime time_from;
   private LocalDateTime time_to;
   private List<Integer> topics_ids;
   private String title_substring;
   public  Filter(){
      topics_ids = new ArrayList<Integer>()   ;
   }
}
