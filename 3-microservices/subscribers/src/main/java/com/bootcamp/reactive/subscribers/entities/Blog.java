package com.bootcamp.reactive.subscribers.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    String id;
    String name;
    String authorId;
    String url;
    String status;
}
