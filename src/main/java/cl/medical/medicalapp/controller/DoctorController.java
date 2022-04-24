package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.document.IDoctorApiDocument;
import cl.medical.medicalapp.entity.DoctorEntity;
import cl.medical.medicalapp.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController implements IDoctorApiDocument {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DoctorEntity>> findAll() {
        List<DoctorEntity> doctors = this.doctorService.findAll();
        return ResponseEntity.ok(doctors);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<DoctorEntity> findById(@PathVariable("id") Integer id) {
        DoctorEntity doctor = this.doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    @Override
    @PostMapping
    public ResponseEntity<DoctorEntity> save(@Valid @RequestBody DoctorEntity doctor) {
        DoctorEntity doctorSaved = this.doctorService.save(doctor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctorSaved.getIdDoctor()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<DoctorEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody DoctorEntity doctor) {
        DoctorEntity doctorUpdated = this.doctorService.update(id, doctor);
        return ResponseEntity.ok(doctorUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<DoctorEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
