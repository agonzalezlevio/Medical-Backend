package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

}
