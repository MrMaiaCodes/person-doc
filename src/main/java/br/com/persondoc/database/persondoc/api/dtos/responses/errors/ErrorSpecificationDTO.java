package br.com.persondoc.database.persondoc.api.dtos.responses.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorSpecificationDTO {

    private String errorCode;

    private String errorMessage;

    private String errorTrace;


}
