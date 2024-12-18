package org.example.DAOImpl;

import org.example.DAO.CityDao;
import org.example.Modell.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.db.MySQLConnection.getConnection;

public class CityDAOJDBC implements CityDao {
    @Override
    public City findById(int id) {
        String sql = "SELECT * FROM CITY WHERE ID = ?";
        City city = null;

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new City(resultSet.getInt("ID"), resultSet.getString("Name"),
                        resultSet.getString("CountryCode"), resultSet.getString("District"),
                        resultSet.getInt("Population"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }


    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM CITY WHERE CountryCode = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("ID"), resultSet.getString("Name"),
                        resultSet.getString("CountryCode"), resultSet.getString("District"),
                        resultSet.getInt("Population")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM CITY WHERE Name LIKE ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("ID"), resultSet.getString("Name"),
                        resultSet.getString("CountryCode"), resultSet.getString("District"),
                        resultSet.getInt("Population")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM CITY";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("ID"), resultSet.getString("Name"),
                        resultSet.getString("CountryCode"), resultSet.getString("District"),
                        resultSet.getInt("Population")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return cities;
    }

    @Override
    public City add(City city) {
        String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?,?,?,?)";

        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());

            int update = statement.executeUpdate();
            if(update > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    city.setId(generatedKeys.getInt(1));
                }
            }
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";

        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());
            statement.setInt(5, city.getId());

            statement.executeUpdate();
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        return city;
    }

    @Override
    public int delete(City city) {
        String sql = "DELETE FROM city WHERE ID = ?";
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setInt(1, city.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return 0;
    }
}
