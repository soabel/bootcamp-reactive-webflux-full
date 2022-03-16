package com.bootcamp.reactive.blog.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value="authors")
public class Author {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Date birthDate;

    @DocumentReference
    List<Blog> blogs;

}
