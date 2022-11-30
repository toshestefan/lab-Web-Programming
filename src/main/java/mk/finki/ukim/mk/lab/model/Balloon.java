package mk.finki.ukim.mk.lab.model;


public class Balloon {

    private  String name;

    private final Long id;
    private  String description;
    private  Manufacturer manufacturer;

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.id = (long) (Math.random()*1000);
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = (long) (Math.random()*1000);
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Balloon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
