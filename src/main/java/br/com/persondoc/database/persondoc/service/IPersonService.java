package br.com.persondoc.database.persondoc.service;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.IService;

import java.util.List;

public interface IPersonService extends IService<Person> {

    List<Person> findPersonByName(String personName) throws PersonNotFoundException;


    void addDocument(String personName, String documentNumber) throws PersonNotFoundException,
            DocumentNotFoundException;
}
