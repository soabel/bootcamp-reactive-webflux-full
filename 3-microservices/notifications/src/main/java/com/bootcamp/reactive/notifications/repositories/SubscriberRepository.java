package com.bootcamp.reactive.notifications.repositories;

import com.bootcamp.reactive.notifications.entities.Subscriber;
import reactor.core.publisher.Flux;

public interface SubscriberRepository {
    Flux<Subscriber> findByBlogId(String blogId);
}
