package com.bootcamp.reactive.notifications.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "notifications")
@ToString
public class Notification {
    @Id
    private String id;
    private String type;
    private String destination;
    private String source;
    private LocalDate date;
    private String blogId;

}
