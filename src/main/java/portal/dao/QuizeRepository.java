package portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Quize;

@Repository
public interface QuizeRepository extends JpaRepository<Quize, Long>{

}
