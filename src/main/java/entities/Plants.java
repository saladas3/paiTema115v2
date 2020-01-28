package entities;

public class Plants {
    private Long plant_id;
    private String plant_name;
    private String scientific_name;

    public Plants(String plant_name, String scientific_name) {
        this.plant_name = plant_name;
        this.scientific_name = scientific_name;
    }

    public Plants(Long plant_id, String plant_name, String scientific_name) {
        this.plant_id = plant_id;
        this.plant_name = plant_name;
        this.scientific_name = scientific_name;
    }

    public Long getPlant_id() {
        return plant_id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setPlant_id(Long plant_id) {
        this.plant_id = plant_id;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public boolean plantIsEmpty() {
        return this.plant_name.isEmpty();
    }

    public boolean plantsAreEqual(Plants plant) {
        return this.plant_name.equals(plant.getPlant_name());
    }

}
