package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.store.entity.Quiz;

import java.util.List;


public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByCategoryId(long categoryId);

}
