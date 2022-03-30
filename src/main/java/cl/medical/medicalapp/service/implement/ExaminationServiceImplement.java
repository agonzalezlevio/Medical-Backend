package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Examination;
import cl.medical.medicalapp.repository.ExaminationRepository;
import cl.medical.medicalapp.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationServiceImplement implements IExaminationService {

    private final ExaminationRepository examinationRepository;

    @Autowired
    public ExaminationServiceImplement(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @Override
    public List<Examination> findAll() {
        return this.examinationRepository.findAll();
    }

    @Override
    public Examination findById(Integer id) {
        Optional<Examination> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalExamination.get();
    }

    @Override
    public Examination save(Examination examination) {
        return this.examinationRepository.save(examination);
    }

    @Override
    public Examination update(Integer id, Examination examination) {
        Optional<Examination> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Examination examinationUpdated = optionalExamination.get();
        examinationUpdated.setDescription(examination.getDescription());
        examinationUpdated.setName(examination.getName());
        return this.examinationRepository.save(examinationUpdated);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Examination> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.examinationRepository.deleteById(id);
    }

}
