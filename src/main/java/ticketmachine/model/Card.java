package ticketmachine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long card_id;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JsonManagedReference
    private Discount discount;

    @ManyToOne
    @JsonManagedReference
    private Validity validity;

    @ManyToOne
    @JsonManagedReference
    private Zone zone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date startDate;

    private double price;

    public Card(User user, Discount discount, Validity validity, Zone zone, Date startDate) {
        this.user = user;
        this.discount = discount;
        this.validity = validity;
        this.zone = zone;
        this.startDate = startDate;
    }

    Card() {}

    public void calcPrice() {
        price = validity.getBasePrice() * zone.getMultiplier() * discount.getAmount();
    }

    public long getCard_id() {
        return card_id;
    }

    public double getPrice() {
        return price;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
