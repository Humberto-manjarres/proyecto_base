package com.project.base.proyectobase.infrastructure.entry_point.exception;

import com.project.base.proyectobase.domain.model.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControladorExcepciones {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception){
        ApiError apiError = new ApiError();
        apiError.setBackenMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage("Error interno en el servidor, vuelva a intentarlo");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> manejarEmpleadoNoEncontrado(BusinessException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.builder()
                        .backenMessage(ex.getLocalizedMessage())
                        .url(request.getRequestURL().toString())
                        .method(request.getMethod())
                        .timeStamp(LocalDateTime.now())
                        .message(ex.getMessage())
                .build());
    }

}
