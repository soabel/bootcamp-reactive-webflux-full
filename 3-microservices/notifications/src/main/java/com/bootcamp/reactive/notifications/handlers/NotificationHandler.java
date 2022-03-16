package com.bootcamp.reactive.notifications.handlers;

import com.bootcamp.reactive.notifications.entities.Notification;
import com.bootcamp.reactive.notifications.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class NotificationHandler {

    @Autowired
    private NotificationService notificationService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(notificationService.findAll(), Notification.class);
    }
}
