package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import ticketmachine.model.Card;
import ticketmachine.model.User;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUser(User user);
}
