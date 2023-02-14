package br.com.persondoc.database.persondoc.api.dtos.responses;

import br.com.persondoc.database.persondoc.api.dtos.requests.PersonDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonListResponseDTO {

    public List<PersonDTO> data;
}
