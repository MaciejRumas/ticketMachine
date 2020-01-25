package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketmachine.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
