package com.bootcamp.reactive.notifications.repositories;

import com.bootcamp.reactive.notifications.entities.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotificationRepository extends ReactiveMongoRepository<Notification,String> {
}
