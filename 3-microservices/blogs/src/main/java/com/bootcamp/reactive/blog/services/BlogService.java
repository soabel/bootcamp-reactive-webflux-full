package com.bootcamp.reactive.blog.services;

import com.bootcamp.reactive.blog.entities.Blog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogService {
    Mono<Blog> findById(String id);
    Flux<Blog> findAll();
    Flux<Blog> findByName(String name);
    Mono<Blog> save(Blog blog);
    Mono<Void> delete(String id);
}
