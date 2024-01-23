package se.fabricioflores.petmarket.repository;

import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;
import se.fabricioflores.petmarket.model.User;

public interface UserRepository extends ListCrudRepository<User, Long>{
  Optional<User> findOneByUsername(String username);
}
