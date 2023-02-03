package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.repository.IDocumentRepository;
import br.com.persondoc.database.persondoc.repository.entities.Document;
import br.com.persondoc.database.persondoc.service.AbstractValidateService;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService  implements IDocumentService {

    @Autowired
    private IDocumentRepository documentRepository;

    @Override
    public Document save(Document document) {

            documentRepository.save(document);
            return document;

    }

    @Override
    public void delete(Document document) {

        documentRepository.delete(document);

    }

    @Override
    public List<Document> listAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document update(Document document) {

        var documentFind = documentRepository.findDocumentByNumber(document.getIdNumber());
        documentFind.setDocumentType(document.getDocumentType());
        documentFind.setIdNumber(document.getIdNumber());
        return documentFind;
    }

    @Override
    public Document findDocumentByNumber(Long documentNumber) {
        var documentFind = documentRepository.findDocumentByNumber(documentNumber);
        if (documentFind != null) {
            return documentFind;
        } else {
            System.out.println("document not found!");
        }
        return null;
    }

}
