package com.bootcamp.reactive.subscribers.services;

import com.bootcamp.reactive.subscribers.entities.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriberService {
    Flux<Subscriber> findAll();
    Mono<Subscriber> findById(Integer id);
    Mono<Subscriber> save(Subscriber subscriber);
    Flux<Subscriber> findByBlogId(String blogId);
}
