package br.com.persondoc.database.persondoc.api.apis;

import br.com.persondoc.database.persondoc.adapters.PersonAdapter;
import br.com.persondoc.database.persondoc.adapters.PersonDTOAdapter;
import br.com.persondoc.database.persondoc.api.dtos.responses.errors.ErrorResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.responses.PersonListResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.responses.PersonResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.PersonDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.PersonsNewDocumentDTO;
import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;
import br.com.persondoc.database.persondoc.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("V1/person")
public class PersonAPI {

    @Autowired
    private IPersonService personService;

    @PostMapping("/new")
    public PersonResponseDTO addWithBody(@RequestBody PersonDTO personDTO) throws SaveMethodException {
        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
                                personService.save(PersonAdapter.convertTo(personDTO))
                        )
                ).build();
    }


    @GetMapping("/find/{person}")
    public PersonListResponseDTO find(@PathVariable("person") String personName) throws PersonNotFoundException {

        return PersonListResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertToList(
                                personService.findPersonByName(personName)
                        )
                ).build();

    }

    @GetMapping("/list")
    public PersonListResponseDTO listAllPersons() {
        return PersonListResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertToList(
                                personService.listAll()
                        )
                ).build();
    }

    @PutMapping("/change/{name}")
    public PersonResponseDTO changeWithBody(@RequestBody PersonDTO personDTO) throws PersonNotFoundException, DocumentNotFoundException {
        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
                                personService.update(
                                        PersonAdapter.convertTo(personDTO)
                                )
                        )
                ).build();
    }

    @PostMapping("/add/document")
    public ResponseEntity addDocument(@RequestBody PersonsNewDocumentDTO newDocumentDTO)
            throws DocumentNotFoundException, PersonNotFoundException {

        personService.addDocument(newDocumentDTO.getPersonName(), newDocumentDTO.getDocumentNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
