package br.com.persondoc.database.persondoc.service;

import br.com.persondoc.database.persondoc.repository.entities.Document;

public interface IDocumentService extends IService<Document> {

    Document findDocumentByNumber(Long documentNumber);
}
