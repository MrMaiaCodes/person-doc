package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.repository.IPersonRepository;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.interfaces.IDocumentService;
import br.com.persondoc.database.persondoc.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IDocumentService documentService;
    
    @Override
    public Person findPersonByName(String personName) {
        return null;
    }

    @Override
    public void addDocument(String personName, String documentNumber) {

    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public List<Person> listAll() {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }
}
