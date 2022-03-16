package com.bootcamp.reactive.notifications.entities;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    private Integer id;
    private String blogId;
    private String blogName;
    private String userId;
}
