package br.com.persondoc.database.persondoc.exceptions;

import br.com.persondoc.database.persondoc.api.dtos.responses.errors.ErrorResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.responses.errors.ErrorSpecificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> PersonNotFoundExceptionHandler(PersonNotFoundException exception) {
        log.info("wound up on exception handler, for not having found a person");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> DocumentNotFoundExceptionHandler(DocumentNotFoundException exception) {
        log.info("wound up on exception handler, for not having found a document");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(SaveMethodException.class)
    public ResponseEntity<ErrorResponseDTO> SaveMethodExceptionHandler(SaveMethodException exception) {
        log.info("wound up on exception handler, for invalid input");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }
}
