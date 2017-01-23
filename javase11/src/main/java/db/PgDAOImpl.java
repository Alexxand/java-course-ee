package db;

import com.google.inject.Inject;
import model.Gun;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Gun.GunBuilder;
import org.postgresql.jdbc2.optional.PoolingDataSource;

public class PgDAOImpl extends DAO {


    /*public PgDAOImpl(){
        super(new PoolingDataSource());
    }*/

    @Inject
    public PgDAOImpl(DataSource dataSource){

        super(dataSource);
    }

    @Override
    public boolean add(String name, double caliber, double rate, InputStream image, long imageSize) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO guns(name,caliber,rate,image) VALUES(?,?,?,?)");
            statement.setString(1,name);
            statement.setDouble(2,caliber);
            statement.setDouble(3,rate);
            statement.setBinaryStream(4,image,(int)imageSize);
            return statement.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private Gun buildGun(ResultSet resultSet) throws SQLException{
        GunBuilder builder = Gun.builder();
        builder.id(resultSet.getInt("id"));
        builder.name(resultSet.getString("name"));
        builder.caliber(resultSet.getDouble("caliber"));
        builder.rate(resultSet.getDouble("rate"));
        builder.image(resultSet.getBinaryStream("image"));
        return builder.build();
    }

    private List<Gun> toList(ResultSet resultSet) throws SQLException{
        List<Gun> gunList = new ArrayList<>();
        while (resultSet.next()) {
            gunList.add(buildGun(resultSet));
        }
        return gunList;
    }

    @Override
    public List<Gun> getRange(int first, int last) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, caliber, rate, image FROM guns ORDER BY id LIMIT ? OFFSET ?");
            statement.setInt(1,last - first + 1);
            statement.setInt(2,first - 1);
            ResultSet resultSet = statement.executeQuery();
            return toList(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Gun> getByPartOfName(String partOfName) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, caliber, rate, image FROM guns WHERE name LIKE ?");
            statement.setString(1,"%" + partOfName + "%");
            ResultSet resultSet = statement.executeQuery();
            return toList(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Gun getById(int id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, caliber, rate, image FROM guns WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return buildGun(resultSet);
            return null;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return deleteAll(Collections.singletonList(id));
    }

    @Override
    public boolean deleteAll(List<Integer> ids) {
        try (Connection connection = dataSource.getConnection()){
            StringBuilder queryStringBuilder = new StringBuilder("DELETE FROM guns WHERE id IN (");
            int nIds = ids.size();
            for(int i = 0; i < nIds - 1; ++i){
                queryStringBuilder.append("?,");
            }
            queryStringBuilder.append("?)");
            String queryString = queryStringBuilder.toString();
            PreparedStatement statement = connection.prepareStatement(queryString);
            for (int i = 0; i < nIds; ++i) {
                statement.setInt(i + 1, ids.get(i));
            }
            return statement.executeUpdate() == nIds;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long getRawsNumber(){
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery("SELECT COUNT(*) FROM guns");
            if (resultSet.next())
                return resultSet.getLong(1);
            else
                return -1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
