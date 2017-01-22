package db;

import model.Gun;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public abstract class DAO {
    protected DataSource dataSource;


    DAO(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public abstract boolean add(String name, double caliber, double rate, InputStream image);
    public abstract List<Gun> getRange(int firstId, int lastId);
    public abstract List<Gun> getByPartOfName(String partOfName);
    public abstract Gun getById(int id);
    public abstract boolean delete(int id);
    public abstract boolean deleteAll(List<Integer> ids);
}
