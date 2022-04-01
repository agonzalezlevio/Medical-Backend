package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Consultation;
import cl.medical.medicalapp.repository.ConsultationRepository;
import cl.medical.medicalapp.service.IConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImplement implements IConsultationService {

    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationServiceImplement(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public List<Consultation> findAll() {
        return this.consultationRepository.findAll();
    }

    @Override
    public Consultation findById(Integer id) {
        Optional<Consultation> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalConsultation.get();
    }

    @Override
    public Consultation save(Consultation consultation) {
        consultation.getDetails().forEach(detail -> {
            detail.setConsultation(consultation);
        });
        return this.consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Integer id, Consultation consultation) {
        Optional<Consultation> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Consultation consultationUpdated = optionalConsultation.get();
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
    public void deleteById(Integer id) {
        Optional<Consultation> optionalConsultation = this.consultationRepository.findById(id);
        if (optionalConsultation.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.consultationRepository.deleteById(id);
    }


}
