package ticketmachine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticket_id;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JsonManagedReference
    private Zone zone;

    @ManyToOne
    @JsonManagedReference
    private Discount discount;

    @ManyToOne
    @JsonManagedReference
    private Duration duration;

    private double price;

    public Ticket(User user, Zone zone, Discount discount, Duration duration) {
        this.user = user;
        this.zone = zone;
        this.discount = discount;
        this.duration = duration;
    }

    Ticket() {}

    public void calcPrice() {
        price = duration.getPrice() * zone.getMultiplier() * discount.getAmount();
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Zone getZone() {
        return zone;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
