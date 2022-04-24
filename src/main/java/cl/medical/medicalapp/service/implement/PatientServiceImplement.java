package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.entity.PatientEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.repository.IPatientRepository;
import cl.medical.medicalapp.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplement implements IPatientService {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientServiceImplement(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientEntity> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PatientEntity findById(Integer id) {
        Optional<PatientEntity> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalPatient.get();
    }

    @Override
    @Transactional
    public PatientEntity save(PatientEntity patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    @Transactional
    public PatientEntity update(Integer id, PatientEntity patient) {
        Optional<PatientEntity> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        PatientEntity patientUpdated = optionalPatient.get();
        patientUpdated.setAddress(patient.getAddress());
        patientUpdated.setEmail(patient.getEmail());
        patientUpdated.setFirstName(patient.getFirstName());
        patientUpdated.setLastName(patient.getLastName());
        patientUpdated.setPhone(patient.getPhone());
        patientUpdated.setRut(patient.getRut());
        return this.patientRepository.save(patientUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<PatientEntity> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.patientRepository.deleteById(id);
    }
}
