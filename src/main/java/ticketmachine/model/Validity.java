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
@Table(name = "validity")
public class Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long validity_id;

    @NotNull
    private String name;

    private int months;

    @NotNull
    private double basePrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "validity")
    @JsonBackReference
    private Set<Card> cards = new HashSet<>();

    public double getBasePrice() {
        return basePrice;
    }

    public long getValidity_id() {
        return validity_id;
    }

    public String getName() {
        return name;
    }

    public int getMonths() {
        return months;
    }

    public Set<Card> getCards() {
        return cards;
    }
}
