package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity, Integer> {

}
