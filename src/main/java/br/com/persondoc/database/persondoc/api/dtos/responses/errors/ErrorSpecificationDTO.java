package br.com.persondoc.database.persondoc.api.dtos.responses.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorSpecificationDTO {

    private String errorCode;

    private String errorMessage;

    //private String errorTrace;


}
