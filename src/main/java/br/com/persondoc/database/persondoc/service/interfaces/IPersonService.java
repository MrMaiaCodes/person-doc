package br.com.persondoc.database.persondoc.service.interfaces;

import br.com.persondoc.database.persondoc.repository.entities.Person;

public interface IPersonService extends IService<Person> {

    Person findPersonByName(String personName);

    void addDocument(String personName, String documentNumber);
}
