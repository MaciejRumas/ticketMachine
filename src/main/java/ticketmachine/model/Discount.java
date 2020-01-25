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
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long discount_id;

    @NotNull
    private String name;

    @NotNull
    private Double amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discount")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discount")
    @JsonBackReference
    private Set<Card> cards = new HashSet<>();

    public Double getAmount() {
        return amount;
    }

    public long getDiscount_id() {
        return discount_id;
    }

    public String getName() {
        return name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Set<Card> getCards() {
        return cards;
    }
}
