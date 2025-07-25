package DAO.Interface;


import java.util.List;


public interface IRepositoryBase<T> {
    T findById(Long id);

    List<T> findAll();

    Long create(T t);

    boolean update(T t);

    boolean delete(Long id);
}
