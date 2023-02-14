package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.repository.IDocumentRepository;
import br.com.persondoc.database.persondoc.repository.entities.Document;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentRepository documentRepository;

    @Override
    public Document save(Document document) {

        documentRepository.save(document);
        return document;

    }

    @Override
    public void delete(Document document) throws DocumentNotFoundException {

        var documentFind = findDocumentByNumber(
                document.getDocumentNumber());

        documentRepository.delete(document);

    }

    @Override
    public List<Document> listAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document update(Document document) {

        var documentFind = documentRepository.findDocumentByNumber(document.getDocumentNumber());
        documentFind.setDocumentType(document.getDocumentType());
        documentFind.setIdNumber(document.getIdNumber());
        documentRepository.save(documentFind);
        return documentFind;
    }

    @Override
    public Document findDocumentByNumber(String documentNumber) throws DocumentNotFoundException {
        var documentFind = documentRepository.findDocumentByNumber(documentNumber);
        if (documentFind != null) {
            return documentFind;
        } else {
            throw new DocumentNotFoundException("Error finding document number " + documentNumber + ".");
        }
    }

}
