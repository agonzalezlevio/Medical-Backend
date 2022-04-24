package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.assembler.ConsultationResumeAssembler;
import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import cl.medical.medicalapp.repository.IConsultationRepository;
import cl.medical.medicalapp.service.IConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImplement implements IConsultationService {

    private final IConsultationRepository consultationRepository;

    private final ConsultationResumeAssembler consultationResumeAssembler;

    @Autowired
    public ConsultationServiceImplement(IConsultationRepository consultationRepository, ConsultationResumeAssembler consultationResumeAssembler) {
        this.consultationRepository = consultationRepository;
        this.consultationResumeAssembler = consultationResumeAssembler;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultationEntity> findAll() {
        return this.consultationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ConsultationEntity findById(Integer id) {
        Optional<ConsultationEntity> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalConsultation.get();
    }

    @Override
    @Transactional
    public ConsultationEntity save(ConsultationEntity consultation) {
        consultation.getDetails().forEach(detail -> {
            detail.setConsultation(consultation);
        });
        return this.consultationRepository.save(consultation);
    }

    @Override
    @Transactional
    public ConsultationEntity update(Integer id, ConsultationEntity consultation) {
        Optional<ConsultationEntity> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        ConsultationEntity consultationUpdated = optionalConsultation.get();
        consultationUpdated.setDate(consultation.getDate());
        consultationUpdated.setPatient(consultation.getPatient());
        consultationUpdated.setSpecialty(consultation.getSpecialty());
        consultationUpdated.setExaminations(consultation.getExaminations());
        consultationUpdated.getDetails().clear();
        consultation.getDetails().forEach(detail -> {
            detail.setConsultation(consultationUpdated);
        });
        consultationUpdated.getDetails().addAll(consultation.getDetails());
        return this.consultationRepository.save(consultationUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<ConsultationEntity> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.consultationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CollectionModel<ConsultationResumeModel> findAllConsultationResume() {
        List<ConsultationEntity> consultationResumeModels = this.consultationRepository.findAll();
        return this.consultationResumeAssembler.toCollectionModel(consultationResumeModels);
    }

    @Override
    @Transactional(readOnly = true)
    public ConsultationResumeModel findByIdConsultationResume(Integer id) {
        Optional<ConsultationEntity> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        ConsultationEntity consultation = optionalConsultation.get();
        ConsultationResumeModel consultationResumeModel = this.consultationResumeAssembler.toModel(consultation);
        return consultationResumeModel;
    }
}
