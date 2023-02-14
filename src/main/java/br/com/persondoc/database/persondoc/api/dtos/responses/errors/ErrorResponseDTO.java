package br.com.persondoc.database.persondoc.api.dtos.responses.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {

    private String errorMessage;

    private ErrorSpecificationDTO data;

    private String metaData;
}
