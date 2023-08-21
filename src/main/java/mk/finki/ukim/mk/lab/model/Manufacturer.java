package mk.finki.ukim.mk.lab.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String country;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manufacturer() {

    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
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
    }
}
