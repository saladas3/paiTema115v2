package repository;

import entities.Characteristics;

import java.sql.*;
import java.util.ArrayList;

public class CharacteristicsRepository {
    private Connection mysql_connection;

    {
        try {
            mysql_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ionlucapai115",
                    "root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CharacteristicsRepository() {}

    public void save(Characteristics characteristic) throws SQLException {
        Statement insert_statement = mysql_connection.createStatement();
        insert_statement.executeUpdate("INSERT INTO characteristics (special_characteristics, color, height, season) " +
                "VALUES ('" + characteristic.getSpecial_characteristics() + "','" + characteristic.getColor() + "','" +
                characteristic.getHeight() + "','" + characteristic.getSeason() + "');");
    }

    public void deleteById(Long Id) throws SQLException {
        Statement delete_statement = mysql_connection.createStatement();
        delete_statement.executeUpdate("DELETE FROM characteristics WHERE characteristic_id = " + Id);
    }

    public Characteristics fetchCharacteristicByName(String characteristicName) throws SQLException {
        Statement select_statement = mysql_connection.createStatement();
        ResultSet result = select_statement.executeQuery("SELECT * FROM characteristics WHERE " +
                "special_characteristics = '" + characteristicName + "'");
        result.next();
        return new Characteristics(result.getLong(1), result.getString(2), result.getString(3),
                result.getFloat(4), result.getString(5));
    }

    public Long fetchCharacteristicId (String special_characteristic) throws SQLException {
        Statement select_statement = mysql_connection.createStatement();
        ResultSet result = select_statement.executeQuery("SELECT characteristic_id FROM characteristics WHERE " +
                "special_characteristics = '" + special_characteristic + "'");
        result.next();
        return result.getLong(1);
    }

    public void modifyCharacteristicByName (String searchedCharacteristic,
                                            String newColor, Float newHeight,
                                            String newSeason) throws SQLException {
        Statement update_statement = mysql_connection.createStatement();
        update_statement.executeUpdate("UPDATE characteristics SET color = '" + newColor + "', height = '" + newHeight + "', season = '" + newSeason + "' " +
                "WHERE special_characteristics = '" + searchedCharacteristic + "'");
    }

    public ArrayList<Characteristics> findAll () throws SQLException {
        ArrayList<Characteristics> characteristics = new ArrayList<>();
        Statement select_statement = mysql_connection.createStatement();
        ResultSet result = select_statement.executeQuery("SELECT * FROM characteristics");

        while (result.next()) {
            Characteristics characteristic = new Characteristics(result.getString(2), result.getString(3),
                    result.getFloat(4), result.getString(5));
            characteristic.setCharacteristic_id(result.getLong(1));
            characteristics.add(characteristic);
        }

        return characteristics;
    }
}
