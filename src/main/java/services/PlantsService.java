package services;

import entities.Plants;
import repository.PlantsRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlantsService {

    private PlantsRepository plantsRepository = new PlantsRepository();

    public PlantsService () {
    }

    public Plants getPlantByName(String plantName) throws SQLException {
        return plantsRepository.fetchPlantByName(plantName);
    }

    public void insertPlant(Plants plant) throws SQLException {
        plantsRepository.save(plant);
    }

    public Long getPlantId(String plantName) throws SQLException {
        return plantsRepository.fetchPlantId(plantName);
    }

    public void removePlantByName(String plantName) throws SQLException {
        Plants p = plantsRepository.fetchPlantByName(plantName);
        plantsRepository.deleteById(p.getPlant_id());
    }

    public ArrayList<Plants> selectAllPlants() throws SQLException {
        return plantsRepository.findAll();
    }

    public void modifyPlantByName(String searchedPlant, String newScientificName) throws SQLException {
        plantsRepository.modifyPlant(searchedPlant, newScientificName);
    }

}
