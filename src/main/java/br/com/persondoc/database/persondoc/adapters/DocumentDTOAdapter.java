package br.com.persondoc.database.persondoc.adapters;

import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import br.com.persondoc.database.persondoc.repository.entities.Document;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentDTOAdapter {

    public static DocumentDTO convertTo(Document document) {

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setDocumentType(document.getDocumentType());
        documentDTO.setDocumentNumber(document.getDocumentNumber());

        return documentDTO;
    }

    public static List<DocumentDTO> convertToList(List<Document> documentList) {
        return documentList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
