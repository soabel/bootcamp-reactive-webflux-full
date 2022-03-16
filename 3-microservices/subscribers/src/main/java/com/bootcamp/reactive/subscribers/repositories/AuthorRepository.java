package com.bootcamp.reactive.subscribers.repositories;

import com.bootcamp.reactive.subscribers.entities.Author;
import reactor.core.publisher.Mono;

public interface AuthorRepository {
    Mono<Author> findById(String id);
}
