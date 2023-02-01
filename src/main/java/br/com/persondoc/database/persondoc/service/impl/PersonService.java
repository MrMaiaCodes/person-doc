package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.repository.IPersonRepository;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.AbstractValidateService;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import br.com.persondoc.database.persondoc.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends AbstractValidateService<Person> implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IDocumentService documentService;

    @Override
    public Person findPersonByName(String personName) {

        var personFind = personRepository.findPersonByName(personName);
        if (personFind != null) {
            return personFind;
        } else {
            System.out.println("Person not found!");
        }
        return null;
    }

    @Override
    public void addDocument(String personName, Long documentNumber) {
        var personFind = findPersonByName(personName);
        var documentFind = documentService.findDocumentByNumber(documentNumber);

        if (personFind != null && documentFind != null) {
            personFind.getDocuments().add(documentFind);
        }

    }

    @Override
    public Person save(Person person) {
        if (validate(person)) {
            personRepository.save(person);
            return person;
        } else {
            return null;
        }
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    @Override
    public Person update(Person person) {

        var personFind = personRepository.findPersonByName(person.getName());
        personFind.setName(person.getName());
        personFind.setAge(person.getAge());
        personFind.setDocuments(person.getDocuments());
        personFind.setId(person.getId());


        return null;
    }

    @Override
    protected boolean validate(Person person) {
        return !validateStringIsNullOrBlank(person.getName())
                && validateLongNotZero(person.getAge());
    }
}
