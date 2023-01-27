package br.com.persondoc.database.persondoc.service;

import java.util.List;

public interface IService<T> {

    T save(T t);

    void delete(T t);

    List<T> listAll();

    T update(T t);

}
