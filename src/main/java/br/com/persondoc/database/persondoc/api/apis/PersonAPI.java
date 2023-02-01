package br.com.persondoc.database.persondoc.api.apis;

import br.com.persondoc.database.persondoc.adapters.PersonAdapter;
import br.com.persondoc.database.persondoc.adapters.PersonDTOAdapter;
import br.com.persondoc.database.persondoc.api.dtos.PersonListResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.PersonResponseDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.PersonDTO;
import br.com.persondoc.database.persondoc.api.dtos.requests.PersonsNewDocumentDTO;
import br.com.persondoc.database.persondoc.repository.entities.Person;
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

    @GetMapping("/find/{name}")
    public PersonListResponseDTO find(@PathVariable("name") String name) {
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
    public ResponseEntity<Person> addDocument(@RequestBody PersonsNewDocumentDTO newDocumentDTO) {
        personService.addDocument(newDocumentDTO.getPersonName(), newDocumentDTO.getDocumentNumber());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
