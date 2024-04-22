package ru.pet.portal.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pet.portal.store.entity.UserE;

public interface UserRepository extends JpaRepository<UserE, Long> {

    Optional<UserE> findByEmail(String email);

    Optional<UserE> findByEmailAndPassword(String email, String password);
}
