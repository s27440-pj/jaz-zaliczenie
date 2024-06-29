package pl.PJATK.jaz_s27440_nbp.clientQuery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class ClientQueryExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFound(HttpClientErrorException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exception from NBP: " + exception.toString());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(HttpClientErrorException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception from NBP: " + exception.toString());
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccess(ResourceAccessException ex) {
        if (ex.getCause() instanceof java.net.ConnectException) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Exception occurred: Service Unavailable");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Exception occurred: " + ex.getMessage());
    }
}
