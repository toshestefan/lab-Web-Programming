package mk.finki.ukim.mk.lab.model.convert;


import javax.persistence.Convert;
import java.io.Serializable;


public class UserFullname implements Serializable {
    private String name;
    private String surname;

    @Override
    public String toString() {
        return name + ' ' + surname;
    }

    public UserFullname(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    public UserFullname() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
