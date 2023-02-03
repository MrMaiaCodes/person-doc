package br.com.persondoc.database.persondoc.adapters;

import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import br.com.persondoc.database.persondoc.repository.entities.Document;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentAdapter {

    public static Document convertTo(DocumentDTO documentDTO){
        return Document.builder()
                .documentType(documentDTO.getDocumentType())
                .documentNumber(documentDTO.getDocumentNumber())
                .build();
    }

    public static List<Document> convertToList(List<DocumentDTO> documentDTO){
        return documentDTO.stream().map(item ->convertTo(item)).collect(Collectors.toList());
    }
}
