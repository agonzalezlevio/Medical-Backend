package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepository extends JpaRepository<SpecialtyEntity, Integer> {

}
