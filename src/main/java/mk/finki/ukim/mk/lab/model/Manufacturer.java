package mk.finki.ukim.mk.lab.model;

public class Manufacturer {

    private final Long id;
    private final String name;
    private final String country;
    private final String address;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public Manufacturer(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.id=(long) (Math.random()*1000);
    }
}
