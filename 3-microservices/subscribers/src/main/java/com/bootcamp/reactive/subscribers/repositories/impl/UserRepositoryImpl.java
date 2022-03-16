package com.bootcamp.reactive.subscribers.repositories.impl;

import com.bootcamp.reactive.subscribers.entities.User;
import com.bootcamp.reactive.subscribers.repositories.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Mono<User> findById(String id) {
        var user = new User();
        user.setId(id);
        user.setName("User " + id);
        return Mono.just(user).delayElement(Duration.ofSeconds(4));

//        return Mono.empty();
    }

}
