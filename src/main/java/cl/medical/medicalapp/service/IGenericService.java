package cl.medical.medicalapp.service;

import java.util.List;

public interface IGenericService<T> {

    List<T> findAll();

    T findById(Integer id);

    T save(T t);

    T update(Integer id, T t);

    void deleteById(Integer id);

}
