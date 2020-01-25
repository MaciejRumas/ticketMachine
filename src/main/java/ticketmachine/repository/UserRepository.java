package ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ticketmachine.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User getByName(String name);
}
