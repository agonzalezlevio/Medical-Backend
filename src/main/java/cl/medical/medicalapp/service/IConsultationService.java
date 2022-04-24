package cl.medical.medicalapp.service;

import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import org.springframework.hateoas.CollectionModel;

public interface IConsultationService extends IGenericService<ConsultationEntity> {

    CollectionModel<ConsultationResumeModel> findAllConsultationResume();

    ConsultationResumeModel findByIdConsultationResume(Integer id);
}
