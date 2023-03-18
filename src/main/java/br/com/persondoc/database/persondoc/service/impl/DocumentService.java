package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;
import br.com.persondoc.database.persondoc.repository.IDocumentRepository;
import br.com.persondoc.database.persondoc.repository.entities.Document;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentRepository documentRepository;

    @Override
    public Document save(Document document) throws SaveMethodException {
        log.info("initialized DocumentService.save");
        if (document != null) {
            log.info("processing save");
            documentRepository.save(document);
            log.info("save complete");
            return document;
        } else {
            log.error("validation failed");
            throw new SaveMethodException("P01", "Invalid Number entered");
        }
    }

    @Override
    public void delete(Document document) throws DocumentNotFoundException {

        log.info("initialized DocumentService.delete");
        var documentFind = findDocumentByNumber(
                document.getDocumentNumber());
        log.info("processing delete");
        documentRepository.delete(document);
        log.info("delete completed");

    }

    @Override
    public List<Document> listAll() {
        log.info("initialized DocumentService.listAll");
        log.info("listAll completed");
        return documentRepository.findAll();
    }

    @Override
    public Document update(Document document) throws DocumentNotFoundException {

        log.info("initialized DocumentService.update");
        var documentFind = documentRepository.findById(document.getIdNumber());
        var documentUpdate = documentFind.orElseThrow(
                () -> new DocumentNotFoundException("P01", "Document Not Found")
        );
        log.info("processing update");
        documentUpdate.setDocumentType(document.getDocumentType());
        documentUpdate.setDocumentNumber(document.getDocumentNumber());

        log.info("update complete");
        return documentRepository.save(documentUpdate);
    }

    @Override
    public Document findDocumentByNumber(String documentNumber) throws DocumentNotFoundException {
        log.info("initialized find");
        var documentFind = documentRepository.findDocumentByNumber(documentNumber);
        if (documentFind != null) {
            log.info("processing find");
            log.info("find completed");
            return documentFind;
        } else {
            log.error("document number not found");
            throw new DocumentNotFoundException("D01", "Error finding document number " + documentNumber + ".");
        }
    }

    //@Override
    //protected boolean validate(Document document) {
    // return validateLongNotZero(document.getDocumentNumber());
    //}

}
