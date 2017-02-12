package pw.rapit.likes.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    class ErrorResponse {

        @JsonProperty("error_code")
        HttpStatus errorCode;

        @JsonProperty("message")
        String message;

        public ErrorResponse(HttpStatus errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public HttpStatus getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
