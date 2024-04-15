package repository;

import java.util.List;

public interface GeneralRepository<T, K> {
    T create(K t);
    List<T> getAll();
    K getById(Long id);
    T update(K t, Long id);
    boolean delete(Long id);
}
