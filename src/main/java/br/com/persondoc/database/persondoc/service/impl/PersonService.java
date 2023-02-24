package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.repository.IPersonRepository;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.AbstractValidateService;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import br.com.persondoc.database.persondoc.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService extends AbstractValidateService<Person> implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IDocumentService documentService;

    @Override
    public Person findPersonByName(String personName) throws PersonNotFoundException {
        log.info("initialized PersonService.findPersonByName");
        var personFind = personRepository.findPersonByName(personName);
        if (personFind != null) {
            log.info("processing findPersonByName");
            log.info("findPersonByName complete");
            return personFind;
        } else {
            log.error("findPersonByName failed");
            throw new PersonNotFoundException("P02", "Error in finding " + personName + ".");
        }
    }

    @Override
    public void addDocument(String personName, String documentNumber) throws PersonNotFoundException,
            DocumentNotFoundException {
        log.info("initialized PersonService.addDocument");
        var personFind = findPersonByName(personName);
        var documentFind = documentService.findDocumentByNumber(documentNumber);
        log.info("processing add");
        personFind.getDocuments().add(documentFind);
        log.info("add complete");
    }

    @Override
    public Person save(Person person) throws SaveMethodException {
        log.info("initialized PersonService.save");
        if (validate(person)) {
            log.info("Processing save");
            personRepository.save(person);
            log.info("save complete");
            return person;
        } else {
            log.error("validation failed");
            throw new SaveMethodException("P01", "Invalid Person saved");
        }
    }

    @Override
    public void delete(Person person) throws PersonNotFoundException {
        log.info("initialized PersonService.delete");
        var personFind = findPersonByName(person.getName());
        if (personFind != null) {
            log.info("Processing delete");
            personRepository.delete(person);
            log.info("delete complete");
        }
    }

    @Override
    public List<Person> listAll() {
        log.info("initialized PersonService.listAll");
        log.info("listAll complete");
        return personRepository.findAll();
    }

    @Override
    public Person update(Person person) throws PersonNotFoundException {
        log.info("initialized PersonService.update");
        var personFind = findPersonByName(person.getName());

        if (personFind != null) {
            log.info("processing update");
            personFind.setName(person.getName());
            personFind.setAge(person.getAge());
            personFind.setDocuments(person.getDocuments());
            personFind.setId(person.getId());
        }
        log.info("update complete");
        return personFind;
    }

    @Override
    protected boolean validate(Person person) {
        return !validateStringIsNullOrBlank(person.getName())
                && validateLongNotZero(person.getAge());
    }
}
