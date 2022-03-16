package com.bootcamp.reactive.blog.repositories;

import com.bootcamp.reactive.blog.entities.Author;
import com.bootcamp.reactive.blog.entities.Blog;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

    @Query(value="{name:{'$regex' : ?0, '$options' : 'i'}, status: ?1 }")
    Flux<Blog> findByNameQuery(String name, String status);
}