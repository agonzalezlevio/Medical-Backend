package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.document.ISpecialtyApiDocument;
import cl.medical.medicalapp.entity.SpecialtyEntity;
import cl.medical.medicalapp.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("specialty")
public class SpecialtyController implements ISpecialtyApiDocument {

    private final ISpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(ISpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpecialtyEntity>> findAll() {
        List<SpecialtyEntity> specialties = this.specialtyService.findAll();
        return ResponseEntity.ok(specialties);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<SpecialtyEntity> findById(@PathVariable("id") Integer id) {
        SpecialtyEntity specialty = this.specialtyService.findById(id);
        return ResponseEntity.ok(specialty);
    }

    @Override
    @PostMapping
    public ResponseEntity<SpecialtyEntity> save(@Valid @RequestBody SpecialtyEntity specialty) {
        SpecialtyEntity specialtySaved = this.specialtyService.save(specialty);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(specialtySaved.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<SpecialtyEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody SpecialtyEntity specialty) {
        SpecialtyEntity specialtyUpdated = this.specialtyService.update(id, specialty);
        return ResponseEntity.ok(specialtyUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<SpecialtyEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.specialtyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
