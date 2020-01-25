package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketmachine.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
