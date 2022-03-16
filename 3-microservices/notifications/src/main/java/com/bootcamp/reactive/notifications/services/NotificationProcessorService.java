package com.bootcamp.reactive.notifications.services;

import com.bootcamp.reactive.notifications.entities.Notification;
import com.bootcamp.reactive.notifications.repositories.NotificationRepository;
import com.bootcamp.reactive.notifications.repositories.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.function.Consumer;

@Slf4j
@Service
public class NotificationProcessorService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Bean
    public Consumer<Notification> notificationConsumer() {
        return (notification) -> {
            log.info("Notification - message received: " + notification);


            this.subscriberRepository.findByBlogId(notification.getBlogId())
                    .flatMap(s->{
                        notification.setDate(LocalDate.now());
                        notification.setDestination(s.getUserId());
                        notification.setSource("BLOG = " + s.getBlogName());
                        return Mono.just(notification);
                    })
                    .flatMap(x->  this.notificationRepository.save(notification))
                    .subscribe(n-> log.info("NOTIFICATION SAVED= " + n) );

        };
    }


}
