package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.entity.ExaminationEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.repository.IExaminationRepository;
import cl.medical.medicalapp.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationServiceImplement implements IExaminationService {

    private final IExaminationRepository examinationRepository;

    @Autowired
    public ExaminationServiceImplement(IExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExaminationEntity> findAll() {
        return this.examinationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ExaminationEntity findById(Integer id) {
        Optional<ExaminationEntity> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalExamination.get();
    }

    @Override
    @Transactional
    public ExaminationEntity save(ExaminationEntity examination) {
        return this.examinationRepository.save(examination);
    }

    @Override
    @Transactional
    public ExaminationEntity update(Integer id, ExaminationEntity examination) {
        Optional<ExaminationEntity> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        ExaminationEntity examinationUpdated = optionalExamination.get();
        examinationUpdated.setDescription(examination.getDescription());
        examinationUpdated.setName(examination.getName());
        return this.examinationRepository.save(examinationUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<ExaminationEntity> optionalExamination = this.examinationRepository.findById(id);
        if (optionalExamination.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.examinationRepository.deleteById(id);
    }

}
