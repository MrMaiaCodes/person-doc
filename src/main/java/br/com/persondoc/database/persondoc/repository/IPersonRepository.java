package br.com.persondoc.database.persondoc.repository;

import br.com.persondoc.database.persondoc.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByName(String name);
}
