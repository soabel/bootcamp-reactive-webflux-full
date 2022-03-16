package com.bootcamp.reactive.subscribers.repositories;

import com.bootcamp.reactive.subscribers.entities.Subscriber;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SubscriberRepository extends ReactiveCrudRepository<Subscriber, Integer> {
    Flux<Subscriber> findByBlogId(String id);
}
