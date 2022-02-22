package portal.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUserName(String name);

	void deleteByUserName(String userName);

	Optional<UserEntity> findByUserNameIsIgnoreCase(String userName);

}
