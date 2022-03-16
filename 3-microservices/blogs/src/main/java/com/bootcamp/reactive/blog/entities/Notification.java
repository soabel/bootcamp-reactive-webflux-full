package com.bootcamp.reactive.blog.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private String id;
    private String type;
    private String destination;
    private String source;
    private String blogId;
}
