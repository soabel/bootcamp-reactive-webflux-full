package com.bootcamp.reactive.subscribers.repositories;

import com.bootcamp.reactive.subscribers.entities.Blog;
import reactor.core.publisher.Mono;

public interface BlogRepository {
    Mono<Blog> findById(String id);
}
