package hei.school.digitalbankapi.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CrudOperations<T>{
    public List<T> findAll() throws SQLException;
    public T save(T toSave) throws SQLException;
    public T update(UUID id, T toUpdate) throws SQLException;
    public void  delete(UUID id) throws SQLException;
}
