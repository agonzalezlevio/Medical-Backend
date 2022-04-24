package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, Integer> {

}
