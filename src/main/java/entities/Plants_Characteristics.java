package entities;


public class Plants_Characteristics {
    private Long plants_characteristics_id;
    private Long plant_id;
    private Long characteristic_id;

    public Plants_Characteristics (Long plant_id, Long characteristic_id) {
        this.plant_id = plant_id;
        this.characteristic_id = characteristic_id;
    }

    public Plants_Characteristics() {};

    public Long getPlants_characteristics_id() {
        return plants_characteristics_id;
    }

    public Long getPlant_id() {
        return plant_id;
    }

    public Long getCharacteristic_id() {
        return characteristic_id;
    }

    public void setPlants_characteristics_id(Long plants_characteristics_id) {
        this.plants_characteristics_id = plants_characteristics_id;
    }

    public void setCharacteristic_id(Long characteristic_id) {
        this.characteristic_id = characteristic_id;
    }

    public void setPlant_id(Long plant_id) {
        this.plant_id = plant_id;
    }
}
