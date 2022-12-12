package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private ShoppingCart cart;
    private String balloonColor;
    private String balloonSize;


    public Order (String balloonColor, String balloonSize,ShoppingCart cart) {
        this.cart = cart;
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
    }

    public Long getId() {
        return id;
    }



    public Order() {
    }

    public void setBalloonColor(String balloonColor) {
        this.balloonColor = balloonColor;
    }

    @Override
    public String toString() {
        return "Order{" +
                "balloonColor='" + balloonColor + '\'' +
                ", balloonSize='" + balloonSize + '\'' +
                '}';
    }

    public void setBalloonSize(String balloonSize) {
        this.balloonSize = balloonSize;
    }


    public String getBalloonColor() {
        return balloonColor;
    }

    public String getBalloonSize() {
        return balloonSize;
    }

    public Order(String balloonColor, String balloonSize) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
    }

}
