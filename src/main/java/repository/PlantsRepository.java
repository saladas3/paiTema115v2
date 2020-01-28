package repository;

import entities.Plants;

import java.sql.*;
import java.util.ArrayList;

public class PlantsRepository {

    private Connection mysql_connection;

    {
        try {
            mysql_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ionlucapai115",
                    "root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlantsRepository() {}

    public void save(Plants plant) throws SQLException {
        Statement insert_statement = mysql_connection.createStatement();
        String query = "INSERT INTO plants (plant_name, scientific_name) " +
                "VALUES('" + plant.getPlant_name() +  "', '" + plant.getScientific_name() + "');";
        insert_statement.executeUpdate(query);
    }

//    public Long getLastInsertedId() throws SQLException {
//        Statement select_statement = mysql_connection.createStatement();
//        ResultSet resultSet = select_statement.executeQuery("SELECT MAX(plant_id) FROM plants;");
//        resultSet.next();
//
//        return resultSet.getLong(1);
//    }

    public void deleteById(Long Id) throws SQLException {
        Statement delete_statement = mysql_connection.createStatement();
        String query = "DELETE FROM plants WHERE plant_id = " + Id;
        System.out.println(query);
        delete_statement.execute(query);
    }

    public Plants fetchPlantByName(String plantName) throws SQLException {
        Statement select_statement = mysql_connection.createStatement();
        String query = "SELECT * FROM plants WHERE plant_name = '" + plantName + "'";
        ResultSet result = select_statement.executeQuery(query);
        result.next();
        System.out.println(query);
        return new Plants(result.getLong(1), result.getString(2), result.getString(3));
    }

    public Long fetchPlantId (String plantName) throws SQLException {
        Statement select_statement = mysql_connection.createStatement();
        String query = "SELECT plant_id FROM plants WHERE plant_name = '" + plantName + "'";
        ResultSet result = select_statement.executeQuery(query);
        result.next();
        System.out.println(query);
        System.out.println(result.getLong(1));
        return result.getLong(1);
    }

    public void modifyPlant (String searchedPlant, String newScientificName) throws SQLException {
        Statement update_statement = mysql_connection.createStatement();
        update_statement.executeUpdate("UPDATE plants SET scientific_name = '" + newScientificName + "' " +
                "WHERE plant_name = '" + searchedPlant + "'");
    }

    public ArrayList<Plants> findAll () throws SQLException {
        ArrayList<Plants> plants = new ArrayList<>();
        Statement select_statement = mysql_connection.createStatement();
        ResultSet result = select_statement.executeQuery("SELECT * FROM plants");

        while (result.next()) {
            Plants plant = new Plants(result.getString(2), result.getString(3));
            plant.setPlant_id(result.getLong(1));
            plants.add(plant);
        }

        return plants;
    }

}
