package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.entity.DoctorEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.repository.IDoctorRepository;
import cl.medical.medicalapp.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImplement implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImplement(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorEntity> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorEntity findById(Integer id) {
        Optional<DoctorEntity> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalDoctor.get();
    }

    @Override
    @Transactional
    public DoctorEntity save(DoctorEntity doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public DoctorEntity update(Integer id, DoctorEntity doctor) {
        Optional<DoctorEntity> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        DoctorEntity doctorUpdated = optionalDoctor.get();
        doctorUpdated.setFirstName(doctor.getFirstName());
        doctorUpdated.setLastName(doctor.getLastName());
        doctorUpdated.setUrlPhoto(doctor.getUrlPhoto());
        return this.doctorRepository.save(doctorUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<DoctorEntity> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.doctorRepository.deleteById(id);
    }
}
