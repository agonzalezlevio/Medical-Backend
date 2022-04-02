package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Patient;
import cl.medical.medicalapp.repository.PatientRepository;
import cl.medical.medicalapp.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplement implements IPatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplement(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findById(Integer id) {
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalPatient.get();
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Patient update(Integer id, Patient patient) {
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Patient patientUpdated = optionalPatient.get();
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
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.patientRepository.deleteById(id);
    }
}
