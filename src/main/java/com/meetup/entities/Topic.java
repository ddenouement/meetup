package com.meetup.entities;

import lombok.*;

/**
 * @author Dmytro Zubko
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private int id;
    private String name;
}

