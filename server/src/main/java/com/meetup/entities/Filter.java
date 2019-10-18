package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**.
 * class representing filter
 */
@Data
@NoArgsConstructor
public class Filter {
   /**.
    * name given by user
    */
   private String name;
}
