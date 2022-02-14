package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.Patient;
import cl.medical.medicalapp.repository.PatientRepository;
import cl.medical.medicalapp.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplement implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient findById(Integer id) throws Exception {
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalPatient.get();
    }

    @Override
    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient update(Integer id, Patient patient) throws Exception {
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        Patient patientUpdated = optionalPatient.get();
        patientUpdated.setAddress(patient.getAddress());
        patientUpdated.setEmail(patient.getEmail());
        patientUpdated.setFirstName(patient.getFirstName());
        patientUpdated.setLastName(patient.getLastName());
        patientUpdated.setPhone(patient.getPhone());
        patientUpdated.setRut(patient.getRut());
        return this.patientRepository.save(patient);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Optional<Patient> optionalPatient = this.patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.patientRepository.deleteById(id);
    }
}
