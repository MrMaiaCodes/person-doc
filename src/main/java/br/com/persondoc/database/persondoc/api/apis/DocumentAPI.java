package br.com.persondoc.database.persondoc.api.apis;


import br.com.persondoc.database.persondoc.adapters.DocumentAdapter;
import br.com.persondoc.database.persondoc.adapters.DocumentDTOAdapter;
import br.com.persondoc.database.persondoc.api.dtos.DocumentListResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.DocumentResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.DocumentDTO;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/V1/Document")
public class DocumentAPI {

    @Autowired
    private IDocumentService documentService;

    @PostMapping("/new")
    public DocumentResponseDTO addWithBody(@RequestBody DocumentDTO documentDTO) {
        return DocumentResponseDTO.builder()
                .data(
                        DocumentDTOAdapter.convertTo(
                                documentService.save(DocumentAdapter.convertTo(documentDTO))
                        )
                )

        .build();
    }

    @GetMapping("/find/{document}")
    public DocumentResponseDTO find(@PathVariable("document") Long documentNumber) {
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

    @PutMapping("/change/{namechange}")
    public DocumentResponseDTO changeWithBody(@RequestBody DocumentDTO documentDTO) {
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
