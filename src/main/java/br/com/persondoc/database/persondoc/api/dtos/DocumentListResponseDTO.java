package br.com.persondoc.database.persondoc.api.dtos;


import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentListResponseDTO {

    public List<DocumentDTO> data;
}
