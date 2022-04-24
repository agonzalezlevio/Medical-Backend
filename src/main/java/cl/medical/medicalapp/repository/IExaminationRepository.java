package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExaminationRepository extends JpaRepository<ExaminationEntity, Integer> {

}
