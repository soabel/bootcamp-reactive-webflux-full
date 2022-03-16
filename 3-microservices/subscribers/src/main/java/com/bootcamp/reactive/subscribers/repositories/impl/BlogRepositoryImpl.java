package com.bootcamp.reactive.subscribers.repositories.impl;

import com.bootcamp.reactive.subscribers.core.exceptions.SusbcriberBaseException;
import com.bootcamp.reactive.subscribers.entities.Blog;
import com.bootcamp.reactive.subscribers.repositories.BlogRepository;
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
public class BlogRepositoryImpl implements BlogRepository {

    private final WebClient client;

    public BlogRepositoryImpl(WebClient.Builder builder,
                              @Value( "${application.urlApiBlogs:http://localhost/blogs}" ) String urlApiBlogs){
        log.info("urlApiBlogs = " + urlApiBlogs);

    //        this.client = builder.baseUrl(urlApiBlog)
    //        .build();

    // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiBlogs)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

    @Override
    public Mono<Blog> findById(String id) {
//        return this.client.get().uri("/{id}", id).accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(Blog.class);


        return this.client.get().uri("/{id}", id).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization","Bearer {token}")
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new SusbcriberBaseException("Server error")))
                .bodyToMono(Blog.class)
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(2))
                        .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                        .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"))
                .doOnSuccess(x-> log.info("LOG SUCCESS"));
    }
}
