package br.com.persondoc.database.persondoc.adapters;

import br.com.persondoc.database.persondoc.api.dtos.requests.PersonDTO;
import br.com.persondoc.database.persondoc.repository.entities.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDTOAdapter {

    public static PersonDTO convertTo(Person person) {

       return PersonDTO.builder()
               .name(person.getName())
               .age(person.getAge())
               .build();
    }

    public static List<PersonDTO> convertToList(List<Person> personList) {
        return personList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
