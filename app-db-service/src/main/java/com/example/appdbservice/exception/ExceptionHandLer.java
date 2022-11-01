package com.example.appdbservice.exception;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.ErrorData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandLer {

    @ExceptionHandler(value = {RestException.class})
    public HttpEntity<?> handling(RestException ex) {

        ApiResult<ErrorData> errorDataApiResult = ApiResult.errorResponse(ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(errorDataApiResult);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public HttpEntity<?> handling(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            errors.add(new ErrorData(
                    objectError.getDefaultMessage(), 400
            ));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity
                .status(498)
                .body(ApiResult.errorResponse("Token Expired!"));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handling(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(500)
                .body(ApiResult.errorResponse("Internal Server Error"));
    }

}
