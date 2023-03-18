package br.com.persondoc.database.persondoc.service.impl;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;
import br.com.persondoc.database.persondoc.repository.IPersonRepository;
import br.com.persondoc.database.persondoc.repository.entities.Document;
import br.com.persondoc.database.persondoc.repository.entities.Person;
import br.com.persondoc.database.persondoc.service.IDocumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.mockito.Mockito.*;

class PersonServiceTest {
    @Mock
    IPersonRepository personRepository;
    @Mock
    IDocumentService documentService;
    @Mock
    Logger log;
    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void testFindPersonByName() {
        when(personRepository.findPersonByName(anyString())).thenReturn(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))));

        Person result = personService.findPersonByName("personName");
        Assertions.assertEquals(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))), result);
    }*/

    @Test
    void testAddDocumentSuccess() throws PersonNotFoundException, DocumentNotFoundException {
        when(personRepository.findPersonByName(any()))
                .thenReturn(List.of(Person.builder()
                        .id(01L)
                        .name("Matthew")
                        .age(21L)
                        .build()));
        when(documentService.findDocumentByNumber(any()))
                .thenReturn(Document.builder()
                        .idNumber(5L)
                        .documentType("RG")
                        .documentNumber("15")
                        .build());

        personService.addDocument("personName", "documentNumber");
    }

    @Test
    void testAddDocumentPersonNotFoundError() throws PersonNotFoundException, DocumentNotFoundException {
        when(personRepository.findPersonByName(any()))
                .thenReturn(null);
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.addDocument("Jack", "33");
        });
        Assertions.assertEquals("P02", thrown.getCode());
        Assertions.assertEquals("Error in finding Jack.", thrown.getMessage());
    }

    @Test
    void testAddDocumentDocumentNotFoundError() throws PersonNotFoundException, DocumentNotFoundException {
        when(personRepository.findPersonByName(any()))
                .thenReturn(List.of(Person.builder().name("Jack").id(17L).build()));
        when(documentService.findDocumentByNumber(anyString()))
                .thenThrow(new DocumentNotFoundException("D01", "Error finding document number 33."));
        DocumentNotFoundException thrown = Assertions.assertThrows(DocumentNotFoundException.class, () -> {
            personService.addDocument("Jack", "33");
        });
        Assertions.assertEquals("D01", thrown.getCode());
        Assertions.assertEquals("Error finding document number 33.", thrown.getMessage());
    }

    @Test
    void testAddDocumentPersonAndDocumentNotFoundError() throws PersonNotFoundException, DocumentNotFoundException {
        when(personRepository.findPersonByName(any())).thenReturn(null);
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.addDocument("Jack", "33");
        });
        when(documentService.findDocumentByNumber(anyString()))
                .thenThrow(new DocumentNotFoundException("D01", "Error finding document number 33."));
        DocumentNotFoundException thrown = Assertions.assertThrows(DocumentNotFoundException.class, () -> {
            personService.addDocument("Jack", "33");
        });
        //Assertions.assertEquals("P02", thrown.getCode());
        //        Assertions.assertEquals("Error in finding Jack.", thrown.getMessage());
        Assertions.assertEquals("P02", thrown.getCode());
        Assertions.assertEquals("Error in finding Jack.", thrown.getMessage());
        Assertions.assertEquals("D01", thrown.getCode());
        Assertions.assertEquals("Error finding document number 33", thrown.getMessage());
    }

    @Test
    void testSaveSuccess() throws SaveMethodException {
        when(personRepository.save(any())).thenReturn(Person.builder().id(25L).build());
        Person result = personService.save(Person.builder().name("Steven").age(30L).build());
        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveNullNameError() throws SaveMethodException {
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personService.save(Person.builder().name("").age(20L).build());

        });

        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid Person saved", thrown.getMessage());
    }

    @Test
    void testSaveAgeZeroError() throws SaveMethodException {
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personService.save(Person.builder().name("Zack").age(0L).build());
        });

        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid Person saved", thrown.getMessage());
    }

    @Test
    void testSaveNullNameAndAgeZeroErrors() throws SaveMethodException {
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personService.save(Person.builder().name("").age(0L).build());
        });

        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid Person Saved", thrown.getMessage());
    }

    //@Test
    //    void testSaveSuccess() throws SaveMethodException {
    //        when(personRepository.save(any())).thenReturn(Person.builder().id(25L).build());
    //        Person result = personService.save(Person.builder().name("Steven").age(30L).build());
    //        Assertions.assertNotNull(result);
    //    }
    @Test
    void testListAllSuccess() {
        when(personRepository.findAll()).thenReturn(List<Person>());
    }

   /* @Test
    void testDelete() {
        when(personRepository.findPersonByName(anyString())).thenReturn(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))));

        personService.delete(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))));
    }



    @Test
    void testUpdate() {
        Person result = personService.update(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))));
        Assertions.assertEquals(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))), result);
    }

    @Test
    void testValidate() {
        boolean result = personService.validate(new Person(Long.valueOf(1), "name", Long.valueOf(1), List.of(new Document(Long.valueOf(1), "documentType", "documentNumber"))));
        Assertions.assertEquals(true, result);
    }*/
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme