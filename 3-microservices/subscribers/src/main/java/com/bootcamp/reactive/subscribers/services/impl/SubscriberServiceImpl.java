package com.bootcamp.reactive.subscribers.services.impl;

import com.bootcamp.reactive.subscribers.core.exceptions.SusbcriberBaseException;
import com.bootcamp.reactive.subscribers.entities.Subscriber;
import com.bootcamp.reactive.subscribers.repositories.AuthorRepository;
import com.bootcamp.reactive.subscribers.repositories.BlogRepository;
import com.bootcamp.reactive.subscribers.repositories.SubscriberRepository;
import com.bootcamp.reactive.subscribers.repositories.UserRepository;
import com.bootcamp.reactive.subscribers.services.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Flux<Subscriber> findAll() {
        return this.subscriberRepository.findAll();
    }

    @Override
    public Mono<Subscriber> findById(Integer id) {
        return this.subscriberRepository.findById(id);
    }

    @Override
    public Mono<Subscriber> save(Subscriber subscriber) {
//        return blogRepository
//                .findById(subscriber.getBlogId())
//                .onErrorResume(e -> Mono.empty())
//                .switchIfEmpty(Mono.error(new SusbcriberBaseException(HttpStatus.NOT_FOUND,"Blog no encontrado")))
//                .flatMap(blog-> {
//                    subscriber.setBlogName(blog.getName());
//                    return this.userRepository.findById(subscriber.getUserId())
//                        .switchIfEmpty(Mono.error(new SusbcriberBaseException(HttpStatus.NOT_FOUND,"Usuario no encontrado")))
//                        .flatMap(x-> this.subscriberRepository.save(subscriber));
//                });

        return blogRepository
                .findById(subscriber.getBlogId())
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.error(new SusbcriberBaseException(HttpStatus.NOT_FOUND,"Blog no encontrado")))
                .zipWhen(b->{
                    return this.authorRepository.findById(b.getAuthorId());
                })
                .flatMap(tupla2-> {
                    subscriber.setBlogName(tupla2.getT1().getName());
                    subscriber.setAuthorName(tupla2.getT2().getName());
                    return this.userRepository.findById(subscriber.getUserId())
                            .switchIfEmpty(Mono.error(new SusbcriberBaseException(HttpStatus.NOT_FOUND,"Usuario no encontrado")))
                            .hasElement()
                            .flatMap(exists-> this.subscriberRepository.save(subscriber));
                });


//        log.info("BEGIN");
//        Mono.zip(blogRepository
//                .findById(subscriber.getBlogId()), this.userRepository.findById(subscriber.getUserId()))
//                .subscribe(tupla->{
//                    log.info("END");
//                    log.info("tupla.getT1() = " + tupla.getT1());
//                    log.info("tupla.getT2() = " + tupla.getT2());
//                });
//
//        return null;


    }

    @Override
    public Flux<Subscriber> findByBlogId(String blogId) {
        return this.subscriberRepository.findByBlogId(blogId);
    }
}
