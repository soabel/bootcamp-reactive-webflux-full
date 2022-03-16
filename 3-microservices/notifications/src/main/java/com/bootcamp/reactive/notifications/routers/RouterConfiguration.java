package com.bootcamp.reactive.notifications.routers;

import com.bootcamp.reactive.notifications.handlers.NotificationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

@Configuration
public class RouterConfiguration {
    @Bean
    public RouterFunction<ServerResponse> blogRoutes(NotificationHandler handler) {
        return RouterFunctions.nest(RequestPredicates.path("/notifications"),
                RouterFunctions
                        .route(GET(""), handler::findAll)
        );
    }

}
