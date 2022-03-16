package com.bootcamp.reactive.notifications.repositories.impl;

import com.bootcamp.reactive.notifications.entities.Subscriber;
import com.bootcamp.reactive.notifications.repositories.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {
    private final WebClient client;

    public SubscriberRepositoryImpl(WebClient.Builder builder,
                                @Value( "${application.urlApiSubscribers:http://localhost/subscribers}" ) String urlApiSubscribers){
        log.info("urlApiSubscribers = " + urlApiSubscribers);

        HttpClient client = HttpClient.create();
        this.client = builder.baseUrl(urlApiSubscribers)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

    @Override
    public Flux<Subscriber> findByBlogId(String blogId){
        return this.client.get().uri("/blog/{blogId}", blogId).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Subscriber.class)
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"));
    }
}
