package br.com.persondoc.database.persondoc.api.dtos.responses;

import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponseDTO {

    public DocumentDTO data;
}
