package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;
import mk.finki.ukim.mk.lab.model.convert.UserFullname;
import mk.finki.ukim.mk.lab.model.converter.UserFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "shop_user")
public class User {

    @Id
    private String username;

    public UserFullname getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(UserFullname userFullname) {
        this.userFullname = userFullname;
    }

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<ShoppingCart> carts;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getPassword() {
        return password;
    }

    public User(String username, String name, String surname, LocalDate dateOfBirth, String password) {
        this.username = username;
        this.userFullname=new UserFullname(name,surname);
        this.password = password;
        this.dateOfBirth=dateOfBirth;
    }

    public User() {

    }
}
