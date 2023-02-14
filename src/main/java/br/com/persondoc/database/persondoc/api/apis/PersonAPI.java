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
    public PersonResponseDTO addWithBody(@RequestBody PersonDTO personDTO) {
        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
                                personService.save(PersonAdapter.convertTo(personDTO))
                        )
                ).build();
    }


    @GetMapping("/find/{person}")
    public PersonResponseDTO find(@PathVariable("person") String personName) throws PersonNotFoundException {

        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
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
    public PersonResponseDTO changeWithBody(@RequestBody PersonDTO personDTO) {
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
    public ResponseEntity addDocument(@RequestBody PersonsNewDocumentDTO newDocumentDTO) {

        try{
            personService.addDocument(newDocumentDTO.getPersonName(), newDocumentDTO.getDocumentNumber());

        } catch (PersonNotFoundException | DocumentNotFoundException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(ErrorResponseDTO
                            .builder()
                            .errorMessage(exception.getMessage())
                            .build())
                    ;

        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
