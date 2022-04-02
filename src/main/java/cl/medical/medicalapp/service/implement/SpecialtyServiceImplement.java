package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Specialty;
import cl.medical.medicalapp.repository.SpecialtyRepository;
import cl.medical.medicalapp.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImplement implements ISpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceImplement(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialty> findAll() {
        return this.specialtyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Specialty findById(Integer id) {
        Optional<Specialty> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalSpecialty.get();
    }

    @Override
    @Transactional
    public Specialty save(Specialty specialty) {
        return this.specialtyRepository.save(specialty);
    }

    @Override
    @Transactional
    public Specialty update(Integer id, Specialty specialty) {
        Optional<Specialty> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Specialty specialtyUpdated = optionalSpecialty.get();
        specialtyUpdated.setName(specialty.getName());
        return this.specialtyRepository.save(specialtyUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Specialty> optionalSpecialty = this.specialtyRepository.findById(id);
        if (optionalSpecialty.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.specialtyRepository.deleteById(id);
    }


}
