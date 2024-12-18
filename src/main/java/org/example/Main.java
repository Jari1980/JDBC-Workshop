package org.example;

import org.example.DAO.CityDao;
import org.example.DAOImpl.CityDAOJDBC;
import org.example.Modell.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.example.db.MySQLConnection.getConnection;

public class Main {
    public static void main(String[] args) {



        CityDao test = new CityDAOJDBC();

        //Find by id
        //City city = test.findById(2);
        //System.out.println(city);

        //Find by code
        //List<City> cities = test.findByCode("USA");
        //for(City element : cities){
        //    System.out.println(element);
        //}

        //Find by name
        //List<City> cities2 = test.findByName("m");
        //for(City element : cities2){
        //    System.out.println(element);
        //}

        //Find all
        //List<City> cities3 = test.findAll();
        //for(City element : cities3){
        //    System.out.println(element);
        //}

        //Add
        //City city = new City("Broccoli", "USA", "Snus", 9000);
        //test.add(city);  //Tested in MySQL city Broccoli added

        //Update
        //City cityFound = test.findById(4080);
        //cityFound.setDistrict("Snuss");
        //test.update(cityFound);
        //System.out.println(cityFound);//Tested in MySQL district changed

        //Delete
        City cityFound = test.findById(4080);
        test.delete(cityFound);//Tested in MySQL row deleted
    }
}