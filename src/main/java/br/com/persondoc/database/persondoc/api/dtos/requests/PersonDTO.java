package br.com.persondoc.database.persondoc.api.dtos.requests;

import br.com.persondoc.database.persondoc.repository.entities.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private String name;

    private Long age;

    private List<DocumentDTO> documents;

}
