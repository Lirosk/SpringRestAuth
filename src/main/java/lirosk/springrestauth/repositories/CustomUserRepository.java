package lirosk.springrestauth.repositories;

import org.springframework.data.repository.CrudRepository;

import lirosk.springrestauth.models.CustomUser;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
    CustomUser findByUsername(String username);

    CustomUser findByIdAndUsername(Long id, String username);
}
