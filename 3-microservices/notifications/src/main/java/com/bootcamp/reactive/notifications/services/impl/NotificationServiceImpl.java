package com.bootcamp.reactive.notifications.services.impl;

import com.bootcamp.reactive.notifications.entities.Notification;
import com.bootcamp.reactive.notifications.repositories.NotificationRepository;
import com.bootcamp.reactive.notifications.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Flux<Notification> findAll() {
        return this.notificationRepository.findAll();
    }



}
