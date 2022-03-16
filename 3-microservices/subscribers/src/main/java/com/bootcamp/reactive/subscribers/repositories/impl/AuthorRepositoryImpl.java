package com.bootcamp.reactive.subscribers.repositories.impl;

import com.bootcamp.reactive.subscribers.core.exceptions.SusbcriberBaseException;
import com.bootcamp.reactive.subscribers.entities.Author;
import com.bootcamp.reactive.subscribers.entities.Blog;
import com.bootcamp.reactive.subscribers.repositories.AuthorRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final WebClient client;

    public AuthorRepositoryImpl(WebClient.Builder builder,
                              @Value( "${application.urlApiAuthors:http://localhost/authors}" ) String urlApiAuthors){
        log.info("urlApiAuthors = " + urlApiAuthors);

        // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiAuthors)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }


    @Override
    public Mono<Author> findById(String id) {

        return this.client.get().uri("/{id}", id).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new SusbcriberBaseException("Server error")))
                .bodyToMono(Author.class)
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"))
                .doOnSuccess(x-> log.info("LOG SUCCESS"));
    }
}
