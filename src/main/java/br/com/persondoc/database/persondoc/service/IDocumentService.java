package br.com.persondoc.database.persondoc.service;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.repository.entities.Document;

public interface IDocumentService extends IService<Document> {

    Document findDocumentByNumber(String documentNumber) throws DocumentNotFoundException;
}
