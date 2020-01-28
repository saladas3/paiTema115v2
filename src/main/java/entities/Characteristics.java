package entities;


public class Characteristics {
    private Long characteristic_id;
    private String special_characteristics;
    private String color;
    private Float height;
    private String season;

    public Characteristics(Long characteristic_id, String special_characteristic, String color, Float height, String season) {
        this.characteristic_id = characteristic_id;
        this.special_characteristics = special_characteristic;
        this.season = season;
        this.height = height;
        this.color = color;
    };

    public Characteristics(String special_characteristic, String color, Float height, String season) {
        this.special_characteristics = special_characteristic;
        this.season = season;
        this.height = height;
        this.color = color;
    }

    public Long getCharacteristic_id() {
        return characteristic_id;
    }

    public String getSpecial_characteristics() {
        return special_characteristics;
    }

    public String getColor() {
        return color;
    }

    public String getSeason(){return season;}

    public Float getHeight(){return height;}

    public void setCharacteristic_id(Long characteristic_id) {
        this.characteristic_id = characteristic_id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setSpecial_characteristics(String special_characteristic) {
        this.special_characteristics = special_characteristic;
    }

    public boolean characteristicIsEmpty() {
        return this.special_characteristics.isEmpty();
    }

    public boolean characteristicsAreEqual(Characteristics characteristic) {
        return this.special_characteristics.equals(characteristic.getSpecial_characteristics());
    }

}
