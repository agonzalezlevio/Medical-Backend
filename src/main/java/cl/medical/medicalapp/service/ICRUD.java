package cl.medical.medicalapp.service;

import java.util.List;

public interface ICRUD<T> {

    List<T> findAll();

    T findById(Integer id) throws Exception;

    T save(T t);

    T update(Integer id, T t) throws Exception;

    void deleteById(Integer id) throws Exception;

}
