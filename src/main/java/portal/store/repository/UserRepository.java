package portal.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import portal.store.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
