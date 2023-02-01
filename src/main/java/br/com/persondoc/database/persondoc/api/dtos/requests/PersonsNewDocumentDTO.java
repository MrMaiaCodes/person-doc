package br.com.persondoc.database.persondoc.api.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonsNewDocumentDTO {

    private String personName;

    private Long documentNumber;
}
