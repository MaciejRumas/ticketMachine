package ticketmachine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long zone_id;

    @NotNull
    private double multiplier;

    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zone")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zone")
    @JsonBackReference
    private Set<Card> cards = new HashSet<>();

    public double getMultiplier() {
        return multiplier;
    }

    public long getZone_id() {
        return zone_id;
    }

    public void setZone_id(long zone_id) {
        this.zone_id = zone_id;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Set<Card> getCards() {
        return cards;
    }
}
