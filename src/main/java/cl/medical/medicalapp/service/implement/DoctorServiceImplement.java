package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Doctor;
import cl.medical.medicalapp.repository.DoctorRepository;
import cl.medical.medicalapp.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImplement implements IDoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImplement(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Integer id) {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalDoctor.get();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Integer id, Doctor doctor) {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Doctor doctorUpdated = optionalDoctor.get();
        doctorUpdated.setFirstName(doctor.getFirstName());
        doctorUpdated.setLastName(doctor.getLastName());
        doctorUpdated.setUrlPhoto(doctor.getUrlPhoto());
        return this.doctorRepository.save(doctorUpdated);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.doctorRepository.deleteById(id);
    }
}
