package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;
import mk.finki.ukim.mk.lab.model.convert.UserFullname;
import mk.finki.ukim.mk.lab.model.converter.UserFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;


@Data
@Entity
@Table(name = "shop_user")
public class User implements UserDetails {

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

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    public String getUsername() {
        return username;
    }




    public void setUsername(String username) {
        this.username = username;
    }


    public User(String username, UserFullname userFullname, String password, LocalDate dateOfBirth, Role role) {
        this.username = username;
        this.userFullname = userFullname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public User(String username, String name, String surname, LocalDate dateOfBirth, String password,Role role) {
        this.username = username;
        this.userFullname=new UserFullname(name,surname);
        this.password = password;
        this.dateOfBirth=dateOfBirth;
        this.role=role;
    }
    public String getPassword() {
        return password;
    }

    public User(String username, String name, String surname, LocalDate dateOfBirth, String password) {
        this.username = username;
        this.userFullname=new UserFullname(name,surname);
        this.password = password;
        this.dateOfBirth=dateOfBirth;
        this.role=Role.ROLE_USER;
    }

    public User() {

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
