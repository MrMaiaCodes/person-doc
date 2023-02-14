package br.com.persondoc.database.persondoc.service;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;

import java.util.List;

public interface IService<T> {

    T save(T t);

    void delete(T t) throws DocumentNotFoundException, PersonNotFoundException;

    List<T> listAll();

    T update(T t) throws PersonNotFoundException, DocumentNotFoundException;

}
