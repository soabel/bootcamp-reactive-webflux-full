package com.bootcamp.reactive.blog.services.impl;


import com.bootcamp.reactive.blog.entities.Blog;
import com.bootcamp.reactive.blog.repositories.BlogRepository;
import com.bootcamp.reactive.blog.services.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Mono<Blog> findById(String id) {
        log.info("CALL findById");
//        return  this.blogRepository.findById(id);
        return  this.blogRepository.findById(id).delayElement(Duration.ofSeconds(4));
//        return  this.blogRepository.findById(id).delayElement(Duration.ofSeconds((int)(Math.random()*10)));
    }

    @Override
    public Flux<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Flux<Blog> findByName(String name) {
       return this.blogRepository.findByNameQuery(name, "activo");
    }

    @Override
    public Mono<Blog> save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.blogRepository.findById(id)
                .doOnNext(b->{
                    System.out.println("doOnNext b = " + b);
                })
                .flatMap(blog-> this.blogRepository.delete(blog));

    }
}
