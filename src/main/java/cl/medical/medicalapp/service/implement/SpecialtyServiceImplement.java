package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.entity.SpecialtyEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.repository.ISpecialtyRepository;
import cl.medical.medicalapp.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImplement implements ISpecialtyService {

    private final ISpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceImplement(ISpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpecialtyEntity> findAll() {
        return this.specialtyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SpecialtyEntity findById(Integer id) {
        Optional<SpecialtyEntity> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalSpecialty.get();
    }

    @Override
    @Transactional
    public SpecialtyEntity save(SpecialtyEntity specialty) {
        return this.specialtyRepository.save(specialty);
    }

    @Override
    @Transactional
    public SpecialtyEntity update(Integer id, SpecialtyEntity specialty) {
        Optional<SpecialtyEntity> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        SpecialtyEntity specialtyUpdated = optionalSpecialty.get();
        specialtyUpdated.setName(specialty.getName());
        return this.specialtyRepository.save(specialtyUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<SpecialtyEntity> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.specialtyRepository.deleteById(id);
    }


}
