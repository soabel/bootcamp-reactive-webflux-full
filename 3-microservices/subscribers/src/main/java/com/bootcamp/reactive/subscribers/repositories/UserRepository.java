package com.bootcamp.reactive.subscribers.repositories;

import com.bootcamp.reactive.subscribers.entities.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> findById(String id);
}
