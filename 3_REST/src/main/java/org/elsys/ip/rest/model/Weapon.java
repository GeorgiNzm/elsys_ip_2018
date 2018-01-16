package org.elsys.ip.rest.model;

public class Weapon {
    private Integer id;
    private String type;
    private String category;
    private String brand;
    private String model;
    private String caliber;
    private Double barrelLength;
    private String capacity;
    private String frameFinish;
    private String material;
    private Double weight;

    public Weapon(Integer id,
                  String type,
                  String category,
                  String brand,
                  String model,
                  String caliber,
                  Double barrelLength,
                  String capacity,
                  String frameFinish,
                  String material,
                  Double weight) {

        this.id = id;
        this.type = type;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.caliber = caliber;
        this.barrelLength = barrelLength;
        this.capacity = capacity;
        this.frameFinish = frameFinish;
        this.material = material;
        this.weight = weight;
    }

    public Weapon() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public Double getBarrelLength() {
        return barrelLength;
    }

    public void setBarrelLength(Double barrelLength) {
        this.barrelLength = barrelLength;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFrameFinish() {
        return frameFinish;
    }

    public void setFrameFinish(String frameFinish) {
        this.frameFinish = frameFinish;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String toCSV() {
        StringBuilder builder = new StringBuilder();

        final String COMMA = ",";

        builder.append(getId().toString().concat(COMMA))
                .append(getType().concat(COMMA))
                .append(getCategory().concat(COMMA))
                .append(getBrand().concat(COMMA))
                .append(getModel().concat(COMMA))
                .append(getCaliber().concat(COMMA))
                .append(getBarrelLength().toString().concat(COMMA))
                .append(getCapacity().concat(COMMA))
                .append(getFrameFinish().concat(COMMA))
                .append(getMaterial().concat(COMMA))
                .append(getWeight().toString().concat(COMMA));

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (!id.equals(weapon.id)) return false;
        if (!type.equals(weapon.type)) return false;
        if (!category.equals(weapon.category)) return false;
        if (!brand.equals(weapon.brand)) return false;
        if (!model.equals(weapon.model)) return false;
        if (!caliber.equals(weapon.caliber)) return false;
        if (!barrelLength.equals(weapon.barrelLength)) return false;
        if (!capacity.equals(weapon.capacity)) return false;
        if (!frameFinish.equals(weapon.frameFinish)) return false;
        if (!material.equals(weapon.material)) return false;
        return weight.equals(weapon.weight);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + caliber.hashCode();
        result = 31 * result + barrelLength.hashCode();
        result = 31 * result + capacity.hashCode();
        result = 31 * result + frameFinish.hashCode();
        result = 31 * result + material.hashCode();
        result = 31 * result + weight.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", caliber='" + caliber + '\'' +
                ", barrelLength=" + barrelLength +
                ", capacity='" + capacity + '\'' +
                ", frameFinish='" + frameFinish + '\'' +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                '}' + "\n";
    }
}
