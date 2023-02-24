package br.com.persondoc.database.persondoc.service;

import br.com.persondoc.database.persondoc.exceptions.DocumentNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.PersonNotFoundException;
import br.com.persondoc.database.persondoc.exceptions.SaveMethodException;

import java.util.List;

public interface IService<T> {

    T save(T t) throws SaveMethodException;

    void delete(T t) throws DocumentNotFoundException, PersonNotFoundException;

    List<T> listAll();

    T update(T t) throws PersonNotFoundException, DocumentNotFoundException;

}
