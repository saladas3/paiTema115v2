package repository;

import entities.Plants_Characteristics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Plants_CharacteristicsRepository {
    private Connection mysql_connection;

    {
        try {
            mysql_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ionlucapai115",
                    "root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Plants_Characteristics plants_characteristics) throws SQLException {
        Statement insert_statement = mysql_connection.createStatement();
        insert_statement.executeUpdate("INSERT INTO plants_characteristics (plant_id, characteristic_id) " +
                "VALUES (" + plants_characteristics.getPlant_id() + "," + plants_characteristics.getCharacteristic_id() + ");");
    }
}
