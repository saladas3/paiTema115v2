package services;

import entities.Characteristics;
import repository.CharacteristicsRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class CharacteristicsService {
    private CharacteristicsRepository characteristicsRepository = new CharacteristicsRepository();

    public CharacteristicsService () {
    }

    public void insertCharacteristic(Characteristics characteristic) throws SQLException {
        characteristicsRepository.save(characteristic);
    }

    public Long getCharacteristicId(String special_characteristic) throws SQLException {
        return characteristicsRepository.fetchCharacteristicId(special_characteristic);
    }

    public ArrayList<Characteristics> selectAllCharacteristics() throws SQLException {
        return characteristicsRepository.findAll();
    }

    public Characteristics getCharacteristicByName(String characteristicName) throws SQLException {
        return characteristicsRepository.fetchCharacteristicByName(characteristicName);
    }

    public void removeCharacteristicFromTable(String characteristicName) throws SQLException {
        Characteristics c = characteristicsRepository.fetchCharacteristicByName(characteristicName);
        characteristicsRepository.deleteById(c.getCharacteristic_id());
    }

    public void modifyCharacteristicByName(String searchedCharacteristic, String newColor, Float newHeight, String newSeason) throws SQLException {
        characteristicsRepository.modifyCharacteristicByName(searchedCharacteristic, newColor, newHeight, newSeason);
    }
}
