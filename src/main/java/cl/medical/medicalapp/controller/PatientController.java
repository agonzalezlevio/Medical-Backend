package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.model.Patient;
import cl.medical.medicalapp.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patientList = this.patientService.findAll();
        return ResponseEntity.ok(patientList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id) {
        Patient patient = this.patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> save(@Valid @RequestBody Patient patient) {
        Patient patientSaved = this.patientService.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientSaved.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Patient> delete(@Valid @PathVariable("id") Integer id) {
        this.patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> update(@PathVariable("id") Integer id, @Valid @RequestBody Patient patient) {
        Patient patientUpdated = this.patientService.update(id, patient);
        return ResponseEntity.ok(patientUpdated);
    }

}
