package cl.medical.medicalapp.service;

import cl.medical.medicalapp.dto.ConsultationResumeDto;
import cl.medical.medicalapp.model.Consultation;
import org.springframework.hateoas.CollectionModel;

public interface IConsultationService extends ICRUD<Consultation> {

    CollectionModel<ConsultationResumeDto> findAllConsultationResume();

    ConsultationResumeDto findByIdConsultationResume(Integer id);
}
