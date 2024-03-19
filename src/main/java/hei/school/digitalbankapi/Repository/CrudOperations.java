package hei.school.digitalbankapi.Repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T>{
    public List<T> findAll() throws SQLException;
    public T save(T toSave) throws SQLException;
    public void update(int id, T toUpdate) throws SQLException;
    public void  delete(int id) throws SQLException;
}
