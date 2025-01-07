package lirosk.springrestauth.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<UserDetails, Long> {
    UserDetails findByUsername(String username);
}
