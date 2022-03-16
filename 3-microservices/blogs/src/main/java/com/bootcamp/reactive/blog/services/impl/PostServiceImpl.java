package com.bootcamp.reactive.blog.services.impl;

import com.bootcamp.reactive.blog.entities.Notification;
import com.bootcamp.reactive.blog.entities.Post;
import com.bootcamp.reactive.blog.repositories.PostRepository;
import com.bootcamp.reactive.blog.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public Mono<Post> save(Post post) {

        return this.postRepository.save(post)
                .flatMap(p->{
                    var notification=new Notification();
                    notification.setType("new post");
                    notification.setDestination("All subscriber");
                    notification.setSource("POST :" + p.getTitle());
                    notification.setBlogId(p.getBlogId());
                    log.info("MESSAGE SEND:" + notification);
                    streamBridge.send("notification-queue", notification);

                    return Mono.just(p);
                });
    }

    @Override
    public Flux<Post> findAll() {
        return this.postRepository.findAll();
    }
}
