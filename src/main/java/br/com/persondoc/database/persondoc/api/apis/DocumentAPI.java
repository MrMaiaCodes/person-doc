package br.com.persondoc.database.persondoc.api.apis;


import br.com.persondoc.database.persondoc.adapters.DocumentAdapter;
import br.com.persondoc.database.persondoc.adapters.DocumentDTOAdapter;
import br.com.persondoc.database.persondoc.api.dtos.responses.DocumentListResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.responses.DocumentResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/V1/Document")
public class DocumentAPI {

    @Autowired
    private IDocumentService documentService;

    @PostMapping("/new")
    public DocumentResponseDTO addWithBody(@RequestBody DocumentDTO documentDTO) throws SaveMethodException {
        return DocumentResponseDTO.builder()
                .data(
                        DocumentDTOAdapter.convertTo(
                                documentService.save(DocumentAdapter.convertTo(documentDTO))
                        )
                )

        .build();
    }

    @GetMapping("/find/{document}")
    public DocumentResponseDTO find(@PathVariable("document") String documentNumber) throws DocumentNotFoundException {
        return DocumentResponseDTO.builder()
                .data(
                        DocumentDTOAdapter.convertTo(
                                documentService.findDocumentByNumber(documentNumber)
                        )
                )
                .build();
    }

    @GetMapping("/list")
    public DocumentListResponseDTO listAllDocuments() {
        return DocumentListResponseDTO.builder()
                .data(
                        DocumentDTOAdapter.convertToList(
                                documentService.listAll()
                        )
                )
                .build();
    }

    @PutMapping("/change/{name-change}")
    public DocumentResponseDTO changeWithBody(@RequestBody DocumentDTO documentDTO)
            throws DocumentNotFoundException, PersonNotFoundException {
        return DocumentResponseDTO.builder()
                .data(
                        DocumentDTOAdapter.convertTo(
                                documentService.update(
                                        DocumentAdapter.convertTo(documentDTO)
                                )
                        )
                ).build();
    }
}
