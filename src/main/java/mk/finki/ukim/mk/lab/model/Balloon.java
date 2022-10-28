package mk.finki.ukim.mk.lab.model;

import lombok.Data;


public class Balloon {

    private final String name;
    private final String description;

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
