package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketmachine.model.Duration;

@Repository
public interface DurationRepository extends JpaRepository<Duration, Long> {
}
