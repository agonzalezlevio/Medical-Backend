package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultationRepository extends JpaRepository<ConsultationEntity, Integer> {
}
