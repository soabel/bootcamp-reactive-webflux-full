package com.bootcamp.reactive.notifications.services;

import com.bootcamp.reactive.notifications.entities.Notification;
import reactor.core.publisher.Flux;

public interface NotificationService {
    Flux<Notification> findAll();
}
