package com.bootcamp.reactive.subscribers.routers;

import com.bootcamp.reactive.subscribers.core.exceptions.GlobalExceptionHandler;
import com.bootcamp.reactive.subscribers.entities.Subscriber;
import com.bootcamp.reactive.subscribers.handlers.SubscriberHandler;
import com.bootcamp.reactive.subscribers.services.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfiguration {



    @Bean
    @RouterOperations(
        {
            @RouterOperation(
                path = "/subscribers",
                produces = {MediaType.APPLICATION_JSON_VALUE},
                method = RequestMethod.GET,
                beanClass = SubscriberHandler.class,
                beanMethod = "findAll",
                operation = @Operation(
                        summary = "Listar suscriptores",
                        description="Listar suscriptores en general",
                        operationId = "findAll",
                        responses = {
                                @ApiResponse(responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(array=@ArraySchema(schema = @Schema(implementation = Subscriber.class)))),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "No se encontró elementos",
                                        content = @Content(schema = @Schema(implementation= GlobalExceptionHandler.HttpError.class))
                                )
                        },
                        parameters = {
                                @Parameter(in = ParameterIn.QUERY, name = "search", required = false )})

            ),
            @RouterOperation(path = "/subscribers/{id}",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.GET,
                    beanClass = SubscriberHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId = "findById",
                            responses = {
                                    @ApiResponse(responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = Subscriber.class))),
                                    @ApiResponse(responseCode = "400", description = "Invalid Subscriptor ID supplied"),
                                    @ApiResponse(responseCode = "404", description = "Employee not found")},
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id" )})
            ),
            @RouterOperation(path = "/subscribers",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = SubscriberHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = Subscriber.class)))},
                            parameters = {},
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Subscriber.class))))
            )

        })
    public RouterFunction<ServerResponse> blogRoutes(SubscriberHandler subscriberHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/subscribers"),
                RouterFunctions
                        .route(GET(""), subscriberHandler::findAll)
                        .andRoute(GET("/{id}"), subscriberHandler::findById)
                        .andRoute(GET("/blog/{blogId}"), subscriberHandler::findByBlogId)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), subscriberHandler::save)
        );
    }

}
