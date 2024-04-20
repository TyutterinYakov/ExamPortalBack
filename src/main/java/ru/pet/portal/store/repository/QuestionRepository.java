package ru.pet.portal.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import portal.store.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
