package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.document.IPatientApiDocument;
import cl.medical.medicalapp.entity.PatientEntity;
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
public class PatientController implements IPatientApiDocument {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PatientEntity>> findAll() {
        List<PatientEntity> patients = this.patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<PatientEntity> findById(@PathVariable("id") Integer id) {
        PatientEntity patient = this.patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @Override
    @PostMapping
    public ResponseEntity<PatientEntity> save(@Valid @RequestBody PatientEntity patient) {
        PatientEntity patientSaved = this.patientService.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientSaved.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<PatientEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody PatientEntity patient) {
        PatientEntity patientUpdated = this.patientService.update(id, patient);
        return ResponseEntity.ok(patientUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<PatientEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
