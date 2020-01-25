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
@Table(name = "duration")
public class Duration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long duration_id;

    @NotNull
    private int minutes;

    @NotNull
    private double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duration")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    public double getPrice() {
        return price;
    }

    public long getDuration_id() {
        return duration_id;
    }

    public int getMinutes() {
        return minutes;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }
}
