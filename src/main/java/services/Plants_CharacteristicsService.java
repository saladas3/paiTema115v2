package services;

import entities.Plants_Characteristics;
import repository.Plants_CharacteristicsRepository;

import java.sql.SQLException;

public class Plants_CharacteristicsService {
    private Plants_CharacteristicsRepository plants_characteristicsRepository = new Plants_CharacteristicsRepository();

    public Plants_CharacteristicsService() {
    }

    public void insertPlantsCharacteristics(Plants_Characteristics plants_characteristics) throws SQLException {
        plants_characteristicsRepository.save(plants_characteristics);
    }

}
