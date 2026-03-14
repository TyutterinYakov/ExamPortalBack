package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.portal.store.entity.PositionE;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<PositionE, UUID> {
    Optional<PositionE> findByName(String name);
}