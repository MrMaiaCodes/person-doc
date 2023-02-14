package br.com.persondoc.database.persondoc.repository;

import br.com.persondoc.database.persondoc.repository.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "select d from Document d where d.documentNumber = :documentNumber")
    Document findDocumentByNumber(String documentNumber);
}
