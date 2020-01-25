package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketmachine.model.Validity;

@Repository
public interface ValidityRepository extends JpaRepository<Validity, Long> {
}
