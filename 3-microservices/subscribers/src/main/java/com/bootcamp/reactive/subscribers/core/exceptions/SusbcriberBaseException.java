package com.bootcamp.reactive.subscribers.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SusbcriberBaseException extends RuntimeException {

    private HttpStatus status  = HttpStatus.BAD_REQUEST;
    private String message;

    public SusbcriberBaseException(String message){
        this.message = message;
    }


}
