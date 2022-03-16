package com.bootcamp.reactive.subscribers.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("subscriber")
public class Subscriber {
    @Id
    @Column("Id")
    private Integer id;
    @Column("BlogId")
    private String blogId;
    @Column("BlogName")
    private String blogName;
    @Column("AuthorName")
    private String authorName;
    @Column("Date")
    private LocalDate date;
    @Column("UserId")
    private String userId;

}
