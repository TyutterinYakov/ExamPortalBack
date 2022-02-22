package portal.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserName(String name);

	void deleteByUserName(String userName);

	Optional<User> findByUserNameIsIgnoreCase(String userName);

}
