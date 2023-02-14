package br.com.persondoc.database.persondoc.repository;

import br.com.persondoc.database.persondoc.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select d from Person d where d.name = :name")
    Person findPersonByName(String name);
}
