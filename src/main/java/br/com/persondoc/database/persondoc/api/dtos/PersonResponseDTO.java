package br.com.persondoc.database.persondoc.api.dtos;

import br.com.persondoc.database.persondoc.api.dtos.requests.PersonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponseDTO {

    public PersonDTO data;
}
